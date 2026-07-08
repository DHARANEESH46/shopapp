package com.example.shopapp.core.data.repository

import com.example.shopapp.core.database.dao.WishlistDao
import com.example.shopapp.core.database.entity.WishlistEntity
import com.example.shopapp.core.domain.model.WishlistItem
import com.example.shopapp.core.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WishlistRepositoryImpl @Inject constructor(
    private val wishlistDao: WishlistDao
) : WishlistRepository {

    override fun getWishlist(userId: Int): Flow<List<WishlistItem>> {
        return wishlistDao.getWishlistByUser(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addToWishlist(item: WishlistItem) {
        wishlistDao.insertWishlistItem(item.toEntity())
    }

    override suspend fun removeFromWishlist(userId: Int, productId: Int) {
        wishlistDao.deleteWishlistItem(userId, productId)
    }

    override fun isInWishlist(userId: Int, productId: Int): Flow<Boolean> {
        return wishlistDao.isInWishlist(userId, productId).map { it > 0 }
    }
}

private fun WishlistEntity.toDomain() = WishlistItem(
    id = id,
    userId = userId,
    productId = productId,
    productTitle = productTitle,
    productPrice = productPrice,
    productThumbnail = productThumbnail
)

private fun WishlistItem.toEntity() = WishlistEntity(
    id = id,
    userId = userId,
    productId = productId,
    productTitle = productTitle,
    productPrice = productPrice,
    productThumbnail = productThumbnail
)