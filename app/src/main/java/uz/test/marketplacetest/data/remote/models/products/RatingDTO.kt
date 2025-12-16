package uz.test.marketplacetest.data.remote.models.products


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RatingDTO(
    @field:Json(name = "count")
    val count: Int?,
    @field:Json(name = "rate")
    val rate: Double?
)