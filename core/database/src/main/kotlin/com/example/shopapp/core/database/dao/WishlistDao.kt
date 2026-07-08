package com.example.shopapp.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shopapp.core.database.entity.WishlistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {

    @Query("SELECT * FROM wishlist_items WHERE userId = :userId")
    fun getWishlistByUser(userId: Int): Flow<List<WishlistEntity>>

    @Query("SELECT * FROM wishlist_items WHERE userId = :userId AND productId = :productId LIMIT 1")
    suspend fun getWishlistItem(userId: Int, productId: Int): WishlistEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishlistItem(item: WishlistEntity)

    @Query("DELETE FROM wishlist_items WHERE userId = :userId AND productId = :productId")
    suspend fun deleteWishlistItem(userId: Int, productId: Int)

    @Query("SELECT COUNT(*) FROM wishlist_items WHERE userId = :userId AND productId = :productId")
    fun isInWishlist(userId: Int, productId: Int): Flow<Int>
}