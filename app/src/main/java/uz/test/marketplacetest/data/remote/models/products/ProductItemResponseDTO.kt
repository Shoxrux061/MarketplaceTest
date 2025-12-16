package uz.test.marketplacetest.data.remote.models.products


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductItemResponseDTO(
    @field:Json(name = "category")
    val category: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "image")
    val image: String?,
    @field:Json(name = "price")
    val price: Double?,
    @field:Json(name = "rating")
    val rating: RatingDTO?,
    @field:Json(name = "title")
    val title: String?
)