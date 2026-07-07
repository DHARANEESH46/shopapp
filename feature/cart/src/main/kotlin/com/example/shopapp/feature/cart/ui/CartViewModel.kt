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

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.getCurrentUserFlow().collect { user ->
                val uid = user?.id ?: return@collect
                cartRepository.getCartItems(uid).collect { items ->
                    _cartItems.value = items
                    _totalPrice.value = items.sumOf { it.productPrice * it.quantity }
                }
            }
        }
    }

    fun removeFromCart(productId: Int) {
        viewModelScope.launch {
            val uid = authRepository.getCurrentUser()?.id ?: return@launch
            cartRepository.removeFromCart(uid, productId)
        }
    }
}