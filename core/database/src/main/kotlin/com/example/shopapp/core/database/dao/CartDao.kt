package com.example.shopapp.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.shopapp.core.database.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_items WHERE userId = :userId")
    fun getCartItemsByUser(userId: Int): Flow<List<CartEntity>>

    @Query("SELECT * FROM cart_items WHERE userId = :userId AND productId = :productId LIMIT 1")
    suspend fun getCartItem(userId: Int, productId: Int): CartEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartEntity)

    @Update
    suspend fun updateCartItem(cartItem: CartEntity)

    @Query("DELETE FROM cart_items WHERE userId = :userId AND productId = :productId")
    suspend fun deleteCartItem(userId: Int, productId: Int)

    @Query("DELETE FROM cart_items WHERE userId = :userId")
    suspend fun clearCartForUser(userId: Int)

    @Query("SELECT SUM(quantity) FROM cart_items WHERE userId = :userId")
    fun getCartCountForUser(userId: Int): Flow<Int?>
}