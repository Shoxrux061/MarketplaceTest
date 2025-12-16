package uz.test.marketplacetest.data.repository

import uz.test.marketplacetest.core.handler.NetworkResult
import uz.test.marketplacetest.core.handler.safeApiCall
import uz.test.marketplacetest.data.mapper.toDomain
import uz.test.marketplacetest.data.remote.service.ProductService
import uz.test.marketplacetest.domain.models.ProductItem
import uz.test.marketplacetest.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val service: ProductService
) : ProductsRepository {

    override suspend fun getProducts(): NetworkResult<List<ProductItem>> {
        return safeApiCall(
            apiCall = {
                service.getProducts()
            },
            mapper = { dto ->
                dto.map { it.toDomain() }
            }
        )
    }

}