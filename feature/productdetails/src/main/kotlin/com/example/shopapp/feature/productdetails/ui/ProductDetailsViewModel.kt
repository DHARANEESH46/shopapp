package com.example.shopapp.feature.productdetails.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.domain.model.ResultState
import com.example.shopapp.core.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailsUiState())
    val uiState: StateFlow<ProductDetailsUiState> = _uiState.asStateFlow()

    private var allProducts = listOf<com.example.shopapp.core.domain.model.Product>()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            getProductsUseCase().collect { state ->
                when (state) {
                    is ResultState.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is ResultState.Success -> {
                        allProducts = state.data
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                products = applySorting(allProducts, it.selectedSort)
                            )
                        }
                    }
                    is ResultState.Error -> _uiState.update { it.copy(isLoading = false, errorMessage = state.message) }
                }
            }
        }
    }

    fun onSortSelected(sort: SortOption) {
        _uiState.update {
            it.copy(
                selectedSort = sort,
                products = applySorting(allProducts, sort),
                showSortSheet = false
            )
        }
    }

    fun showSortSheet() { _uiState.update { it.copy(showSortSheet = true) } }
    fun hideSortSheet() { _uiState.update { it.copy(showSortSheet = false) } }

    private fun applySorting(
        products: List<com.example.shopapp.core.domain.model.Product>,
        sort: SortOption
    ) = when (sort) {
        SortOption.NAME_A_Z     -> products.sortedBy { it.title }
        SortOption.NAME_Z_A     -> products.sortedByDescending { it.title }
        SortOption.PRICE_HIGH_LOW -> products.sortedByDescending { it.price }
        SortOption.PRICE_LOW_HIGH -> products.sortedBy { it.price }
    }
}