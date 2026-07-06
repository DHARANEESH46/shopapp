package com.example.shopapp.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("id") val id: Int,
    @SerialName("username") val username: String,
    @SerialName("email") val email: String,
    @SerialName("firstName") val firstName: String,
    @SerialName("lastName") val lastName: String,
    @SerialName("image") val image: String,
)