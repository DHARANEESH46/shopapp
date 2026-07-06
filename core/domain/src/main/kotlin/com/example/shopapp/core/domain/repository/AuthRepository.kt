package com.example.shopapp.core.domain.repository

import com.example.shopapp.core.domain.model.AuthState
import com.example.shopapp.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(username: String, password: String): Flow<AuthState>
    suspend fun logout()
    fun isLoggedIn(): Boolean
    fun getCurrentUser(): User?
    fun getCurrentUserFlow(): Flow<User?>
}