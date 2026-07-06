package com.example.shopapp.feature.individualproduct.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.domain.model.CartItem
import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.ResultState
import com.example.shopapp.core.domain.repository.AuthRepository
import com.example.shopapp.core.domain.repository.CartRepository
import com.example.shopapp.core.domain.usecase.GetProductByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IndividualProductViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val cartRepository: CartRepository,
    private val authRepository: AuthRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val productId: Int = checkNotNull(savedStateHandle["productId"])
    private val currentUserId: Int get() = authRepository.getCurrentUser()?.id ?: 0

    private val _productState = MutableStateFlow<ResultState<Product>>(ResultState.Loading)
    val productState: StateFlow<ResultState<Product>> = _productState.asStateFlow()

    private val _cartCount = MutableStateFlow(0)
    val cartCount: StateFlow<Int> = _cartCount.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    init {
        loadProduct()
        observeCartCount()
    }

    private fun loadProduct() {
        viewModelScope.launch {
            getProductByIdUseCase(productId).collect {
                _productState.value = it
            }
        }
    }

    private fun observeCartCount() {
        viewModelScope.launch {
            cartRepository.getCartCount(currentUserId).collect {
                _cartCount.value = it
            }
        }
    }

    fun addToCart(product: Product, quantity: Int) {
        viewModelScope.launch {
            cartRepository.addToCart(
                CartItem(
                    userId = currentUserId,
                    productId = product.id,
                    productTitle = product.title,
                    productPrice = product.price,
                    productThumbnail = product.thumbnail,
                    quantity = quantity
                )
            )
            _snackbarMessage.value = "${product.title} added to cart!"
        }
    }

    fun clearSnackbar() {
        _snackbarMessage.value = null
    }
}