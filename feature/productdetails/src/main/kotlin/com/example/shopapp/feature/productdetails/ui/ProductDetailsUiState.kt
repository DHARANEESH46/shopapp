package com.example.shopapp.feature.productdetails.ui

import com.example.shopapp.core.domain.model.Product

enum class SortOption {
    NAME_A_Z,
    NAME_Z_A,
    PRICE_HIGH_LOW,
    PRICE_LOW_HIGH
}

data class ProductDetailsUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val selectedSort: SortOption = SortOption.NAME_A_Z,
    val showSortSheet: Boolean = false
)