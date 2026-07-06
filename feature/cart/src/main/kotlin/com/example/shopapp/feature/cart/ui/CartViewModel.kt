package com.example.shopapp.feature.cart.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.domain.model.CartItem
import com.example.shopapp.core.domain.repository.AuthRepository
import com.example.shopapp.core.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val userId: Int get() = authRepository.getCurrentUser()?.id ?: 0

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice.asStateFlow()

    init {
        viewModelScope.launch {
            cartRepository.getCartItems(userId).collect { items ->
                _cartItems.value = items
                _totalPrice.value = items.sumOf { it.productPrice * it.quantity }
            }
        }
    }

    fun removeFromCart(productId: Int) {
        viewModelScope.launch {
            cartRepository.removeFromCart(userId, productId)
        }
    }
}