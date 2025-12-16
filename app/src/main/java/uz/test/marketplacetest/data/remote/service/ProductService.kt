package uz.test.marketplacetest.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import uz.test.marketplacetest.data.remote.models.products.ProductItemResponseDTO

interface ProductService {

    @GET("/products")
    suspend fun getProducts(): Response<List<ProductItemResponseDTO>>

}