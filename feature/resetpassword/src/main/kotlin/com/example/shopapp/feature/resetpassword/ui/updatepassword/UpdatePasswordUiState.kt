package com.example.shopapp.feature.resetpassword.ui.updatepassword

data class UpdatePasswordUiState(
    val newPassword: String = "",
    val confirmPassword: String = "",
    val newPasswordVisible: Boolean = false,
    val confirmPasswordVisible: Boolean = false
)