package com.example.shopapp.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val productId: Int,
    val productTitle: String,
    val productPrice: Double,
    val productThumbnail: String,
    val quantity: Int
)