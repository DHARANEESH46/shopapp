package com.example.shopapp.core.domain.usecase

import com.example.shopapp.core.domain.model.AuthState
import com.example.shopapp.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(username: String, password: String): Flow<AuthState> {
        return authRepository.login(username, password)
    }
}