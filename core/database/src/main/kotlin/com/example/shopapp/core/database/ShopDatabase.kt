package com.example.shopapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopapp.core.database.dao.CartDao
import com.example.shopapp.core.database.dao.ProductDao
import com.example.shopapp.core.database.dao.WishlistDao
import com.example.shopapp.core.database.entity.CartEntity
import com.example.shopapp.core.database.entity.ProductEntity
import com.example.shopapp.core.database.entity.WishlistEntity

@Database(
    entities = [ProductEntity::class, CartEntity::class, WishlistEntity::class],
    version = 4,
    exportSchema = false
)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
    abstract fun wishlistDao(): WishlistDao
}