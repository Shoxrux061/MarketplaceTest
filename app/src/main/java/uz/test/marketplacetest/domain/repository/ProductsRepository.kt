package uz.test.marketplacetest.domain.repository

import uz.test.marketplacetest.core.handler.NetworkResult
import uz.test.marketplacetest.domain.models.ProductItemModel

interface ProductsRepository {

    suspend fun getProducts(): NetworkResult<List<ProductItemModel>>

}