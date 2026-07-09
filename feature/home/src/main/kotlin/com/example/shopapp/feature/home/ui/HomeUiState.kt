package com.example.shopapp.feature.home.ui

import com.example.shopapp.core.domain.model.Product

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
    val searchQuery: String = "",
    val cartCount: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)