package com.example.shopapp.core.domain.repository

import com.example.shopapp.core.domain.model.WishlistItem
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {
    fun getWishlist(userId: Int): Flow<List<WishlistItem>>
    suspend fun addToWishlist(item: WishlistItem)
    suspend fun removeFromWishlist(userId: Int, productId: Int)
    fun isInWishlist(userId: Int, productId: Int): Flow<Boolean>
}