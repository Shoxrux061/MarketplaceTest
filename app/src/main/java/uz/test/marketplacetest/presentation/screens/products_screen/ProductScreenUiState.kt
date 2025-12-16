package uz.test.marketplacetest.presentation.screens.products_screen

import uz.test.marketplacetest.domain.models.ProductItem

data class ProductScreenUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val products: List<ProductItem> = emptyList()
)
