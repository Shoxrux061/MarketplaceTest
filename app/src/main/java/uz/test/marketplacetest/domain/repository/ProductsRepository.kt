package uz.test.marketplacetest.domain.repository

import uz.test.marketplacetest.core.handler.NetworkResult
import uz.test.marketplacetest.domain.models.ProductItem

interface ProductsRepository {

    suspend fun getProducts(): NetworkResult<List<ProductItem>>

}