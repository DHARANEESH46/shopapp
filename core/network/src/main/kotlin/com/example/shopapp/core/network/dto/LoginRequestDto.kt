package com.example.shopapp.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
    @SerialName("expiresInMins") val expiresInMins: Int = 30
)