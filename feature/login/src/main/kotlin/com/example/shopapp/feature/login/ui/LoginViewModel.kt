package com.example.shopapp.feature.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.domain.model.AuthState
import com.example.shopapp.core.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUsernameChange(value: String) {
        _uiState.update { it.copy(username = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    fun login() {
        viewModelScope.launch {
            loginUseCase(
                username = _uiState.value.username,
                password = _uiState.value.password
            ).collect { state ->
                when (state) {
                    is AuthState.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is AuthState.Success -> _uiState.update { it.copy(isLoading = false, isLoginSuccess = true) }
                    is AuthState.Error -> _uiState.update { it.copy(isLoading = false, errorMessage = state.message) }
                    is AuthState.Idle -> _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    fun resetState() {
        _uiState.update { it.copy(isLoginSuccess = false, errorMessage = null) }
    }
}