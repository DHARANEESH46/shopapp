package com.example.shopapp.feature.account.ui

import com.example.shopapp.core.domain.model.User

data class AccountUiState(
    val user: User? = null,
    val isLoading: Boolean = false
)