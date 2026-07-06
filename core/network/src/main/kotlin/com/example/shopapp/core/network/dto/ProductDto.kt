package com.example.shopapp.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("price") val price: Double,
    @SerialName("discountPercentage") val discountPercentage: Double,
    @SerialName("rating") val rating: Double,
    @SerialName("stock") val stock: Int,
    @SerialName("brand") val brand: String? = null,
    @SerialName("category") val category: String,
    @SerialName("thumbnail") val thumbnail: String,
    @SerialName("images") val images: List<String>,
    @SerialName("reviews") val reviews: List<ReviewDto> = emptyList()
)