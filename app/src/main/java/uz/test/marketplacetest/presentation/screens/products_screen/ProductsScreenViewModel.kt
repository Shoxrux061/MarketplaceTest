package uz.test.marketplacetest.presentation.screens.products_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.test.marketplacetest.core.handler.NetworkResult
import uz.test.marketplacetest.domain.use_case.GetProductListUseCase
import javax.inject.Inject

@HiltViewModel
class ProductsScreenViewModel @Inject constructor(
    private val getProductsUseCase: GetProductListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductScreenUiState())
    val uiState: StateFlow<ProductScreenUiState> = _uiState

    init {
        getProductsList()
    }

    private fun getProductsList() {

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }

            getProductsUseCase.invoke().let { result ->
                when (result) {

                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.error.toString()
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = null,
                                products = result.data
                            )
                        }
                    }
                }
            }
        }
    }
}