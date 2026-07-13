package com.example.shopapp.feature.resetpassword.ui.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(VerificationUiState())
    val uiState: StateFlow<VerificationUiState> = _uiState.asStateFlow()

    private var timerJob: kotlinx.coroutines.Job? = null

    init {
        startTimer()
    }

    fun onDigitChange(index: Int, value: String) {
        val digit = value.takeLast(1).filter { it.isDigit() }
        _uiState.update { state ->
            val updated = state.codeDigits.toMutableList().apply { this[index] = digit }
            state.copy(codeDigits = updated)
        }
    }

    fun onResendClick() {
        startTimer()
    }

    private fun startTimer() {
        timerJob?.cancel()
        _uiState.update { it.copy(secondsRemaining = 185) }
        timerJob = viewModelScope.launch {
            while (_uiState.value.secondsRemaining > 0) {
                delay(1000)
                _uiState.update { it.copy(secondsRemaining = it.secondsRemaining - 1) }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}