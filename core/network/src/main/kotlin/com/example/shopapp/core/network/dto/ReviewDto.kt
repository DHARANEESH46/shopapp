package com.example.shopapp.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewDto(
    @SerialName("rating") val rating: Int,
    @SerialName("comment") val comment: String,
    @SerialName("date") val date: String,
    @SerialName("reviewerName") val reviewerName: String,
    @SerialName("reviewerEmail") val reviewerEmail: String
)