package com.example.shopapp.feature.resetpassword.ui.verification

data class VerificationUiState(
    val codeDigits: List<String> = listOf("", "", "", ""),
    val secondsRemaining: Int = 185
)