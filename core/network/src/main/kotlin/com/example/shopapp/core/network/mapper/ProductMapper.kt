package com.example.shopapp.core.network.mapper

import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.Review
import com.example.shopapp.core.domain.model.User
import com.example.shopapp.core.network.dto.LoginResponseDto
import com.example.shopapp.core.network.dto.ProductDto
import com.example.shopapp.core.network.dto.ReviewDto

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand ?: "Unknown",
        category = category,
        thumbnail = thumbnail,
        images = images,
        reviews = reviews.map { it.toDomain() }
    )
}

fun ReviewDto.toDomain(): Review {
    return Review(
        rating = rating,
        comment = comment,
        date = date,
        reviewerName = reviewerName
    )
}

fun LoginResponseDto.toDomain(): User {
    return User(
        id = id,
        username = username,
        email = email,
        firstName = firstName,
        lastName = lastName,
        image = image
    )
}