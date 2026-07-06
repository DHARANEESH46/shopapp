package com.example.shopapp.core.database.mapper

import com.example.shopapp.core.database.entity.ProductEntity
import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.Review


private const val REVIEW_SEPARATOR = ";;;"
private const val FIELD_SEPARATOR = "|||"

fun ProductEntity.toDomain(): Product {
    val parsedReviews = if (reviews.isBlank()) {
        emptyList()
    } else {
        reviews.split(REVIEW_SEPARATOR).mapNotNull { entry ->
            val parts = entry.split(FIELD_SEPARATOR)
            if (parts.size == 4) {
                Review(
                    reviewerName = parts[0],
                    rating = parts[1].toIntOrNull() ?: 0,
                    comment = parts[2],
                    date = parts[3]
                )
            } else null
        }
    }

    return Product(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail,
        images = images.split(","),
        reviews = parsedReviews
    )
}

fun Product.toEntity(): ProductEntity {
    val reviewsString = reviews.joinToString(REVIEW_SEPARATOR) { review ->
        "${review.reviewerName}${FIELD_SEPARATOR}${review.rating}${FIELD_SEPARATOR}${review.comment}${FIELD_SEPARATOR}${review.date}"
    }

    return ProductEntity(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail,
        images = images.joinToString(","),
        reviews = reviewsString
    )
}