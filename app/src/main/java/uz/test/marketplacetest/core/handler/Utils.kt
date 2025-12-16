package uz.test.marketplacetest.core.handler

import android.util.Log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T, R> safeApiCall(
    apiCall: suspend () -> Response<T>,
    mapper: (T) -> R
): NetworkResult<R> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                NetworkResult.Success(mapper(body))
            } else {
                NetworkResult.Error(AppError.Server("Response body is null"))
            }
        } else {
            NetworkResult.Error(AppError.Server("HTTP ${response.code()} ${response.message()}"))
        }
    } catch (e: HttpException) {
        NetworkResult.Error(AppError.Server("HTTP ${e.code()} ${e.message()}"))
    } catch (e: IOException) {
        NetworkResult.Error(AppError.Server("Network error: ${e.message}"))
    } catch (e: CancellationException) {
        throw e // корутины отмены не оборачиваем
    } catch (e: Throwable) {
        NetworkResult.Error(AppError.Unknown(e))
    }
}
