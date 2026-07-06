package com.example.shopapp.core.data.repository

import com.example.shopapp.core.database.dao.CartDao
import com.example.shopapp.core.database.entity.CartEntity
import com.example.shopapp.core.domain.model.CartItem
import com.example.shopapp.core.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {

    override fun getCartItems(userId: Int): Flow<List<CartItem>> {
        return cartDao.getCartItemsByUser(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addToCart(item: CartItem) {

        val existing = cartDao.getCartItem(item.userId, item.productId)
        if (existing != null) {

            cartDao.updateCartItem(
                existing.copy(quantity = existing.quantity + item.quantity)
            )
        } else {
            cartDao.insertCartItem(item.toEntity())
        }
    }

    override suspend fun removeFromCart(userId: Int, productId: Int) {
        cartDao.deleteCartItem(userId, productId)
    }

    override suspend fun clearCartForUser(userId: Int) {
        cartDao.clearCartForUser(userId)
    }

    override fun getCartCount(userId: Int): Flow<Int> {
        return cartDao.getCartCountForUser(userId).map { it ?: 0 }
    }
}


private fun CartEntity.toDomain(): CartItem = CartItem(
    id = id,
    userId = userId,
    productId = productId,
    productTitle = productTitle,
    productPrice = productPrice,
    productThumbnail = productThumbnail,
    quantity = quantity
)

private fun CartItem.toEntity(): CartEntity = CartEntity(
    id = id,
    userId = userId,
    productId = productId,
    productTitle = productTitle,
    productPrice = productPrice,
    productThumbnail = productThumbnail,
    quantity = quantity
)