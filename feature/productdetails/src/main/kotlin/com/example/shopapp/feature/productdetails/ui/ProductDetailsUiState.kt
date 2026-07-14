package com.example.shopapp.feature.productdetails.ui

import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.SortOption

data class ProductDetailsUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val errorMessage: String? = null,
    val selectedSort: SortOption = SortOption.NAME_A_Z,
    val showSortSheet: Boolean = false,
    val currentSkip: Int = 0,
    val hasReachedEnd: Boolean = false
)