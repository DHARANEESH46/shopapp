package com.example.shopapp.core.domain.model

data class WishlistItem(
    val id: Int = 0,
    val userId: Int,
    val productId: Int,
    val productTitle: String,
    val productPrice: Double,
    val productThumbnail: String
)