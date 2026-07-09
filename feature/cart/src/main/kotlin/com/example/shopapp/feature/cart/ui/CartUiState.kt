package com.example.shopapp.feature.cart.ui

import com.example.shopapp.core.domain.model.CartItem

data class CartUiState(
    val cartItems: List<CartItem> = emptyList(),
    val totalPrice: Double = 0.0,
    val isLoading: Boolean = false
)