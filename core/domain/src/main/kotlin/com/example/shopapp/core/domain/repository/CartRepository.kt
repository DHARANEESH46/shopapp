package com.example.shopapp.core.domain.repository

import com.example.shopapp.core.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(userId: Int): Flow<List<CartItem>>
    suspend fun addToCart(item: CartItem)
    suspend fun removeFromCart(userId: Int, productId: Int)
    suspend fun clearCartForUser(userId: Int)
    fun getCartCount(userId: Int): Flow<Int>
}