package com.example.shopapp.feature.account.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.domain.model.User
import com.example.shopapp.core.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.getCurrentUserFlow().collect { user ->
                _user.value = user
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}