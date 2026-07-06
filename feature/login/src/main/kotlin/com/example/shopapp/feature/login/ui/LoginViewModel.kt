package com.example.shopapp.feature.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.domain.model.AuthState
import com.example.shopapp.core.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible: StateFlow<Boolean> = _passwordVisible.asStateFlow()

    fun onUsernameChange(value: String) {
        _username.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }

    fun login() {
        viewModelScope.launch {
            loginUseCase(
                username = _username.value,
                password = _password.value
            ).collect { state ->
                _authState.value = state
            }
        }
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }
}