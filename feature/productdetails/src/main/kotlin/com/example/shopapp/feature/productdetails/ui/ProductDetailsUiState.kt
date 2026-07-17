package com.example.shopapp.feature.productdetails.ui

import com.example.shopapp.core.domain.model.SortOption

data class ProductDetailsUiState(
    val selectedSort: SortOption = SortOption.NAME_A_Z,
    val showSortSheet: Boolean = false
)