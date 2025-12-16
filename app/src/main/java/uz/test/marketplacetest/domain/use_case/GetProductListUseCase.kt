package uz.test.marketplacetest.domain.use_case

import uz.test.marketplacetest.core.handler.NetworkResult
import uz.test.marketplacetest.domain.models.ProductItem
import uz.test.marketplacetest.domain.repository.ProductsRepository
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val repository: ProductsRepository
) {

    suspend operator fun invoke(): NetworkResult<List<ProductItem>> {
        return repository.getProducts()
    }

}