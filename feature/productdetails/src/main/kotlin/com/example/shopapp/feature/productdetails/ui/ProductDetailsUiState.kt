package com.example.shopapp.feature.productdetails.ui

import com.example.shopapp.core.domain.model.Product

data class ProductDetailsUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)