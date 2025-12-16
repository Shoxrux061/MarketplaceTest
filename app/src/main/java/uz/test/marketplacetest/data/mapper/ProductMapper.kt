package uz.test.marketplacetest.data.mapper

import uz.test.marketplacetest.data.remote.models.products.ProductItemResponseDTO
import uz.test.marketplacetest.data.remote.models.products.RatingDTO
import uz.test.marketplacetest.domain.models.ProductItemModel
import uz.test.marketplacetest.domain.models.Rating

fun ProductItemResponseDTO.toDomain(): ProductItemModel {

    return ProductItemModel(
        category = category ?: "",
        description = description ?: "",
        id = id ?: 0,
        image = image ?: "",
        price = price ?: 0.0,
        rating = rating?.toDomain() ?: Rating(),
        title = title ?: ""

    )

}

fun RatingDTO.toDomain(): Rating {
    return Rating(
        count = count ?: 0,
        rate = rate ?: 0.0
    )
}