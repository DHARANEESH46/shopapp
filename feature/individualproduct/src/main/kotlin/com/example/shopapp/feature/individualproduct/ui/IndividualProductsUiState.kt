package com.example.shopapp.feature.individualproduct.ui

import com.example.shopapp.core.domain.model.Product

data class IndividualProductUiState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val cartCount: Int = 0,
    val isInWishlist: Boolean = false,
    val snackbarMessage: String? = null
)