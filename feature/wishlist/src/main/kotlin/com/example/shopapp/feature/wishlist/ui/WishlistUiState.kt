package com.example.shopapp.feature.wishlist.ui

import com.example.shopapp.core.domain.model.WishlistItem

data class WishlistUiState(
    val wishlistItems: List<WishlistItem> = emptyList(),
    val isLoading: Boolean = false
)