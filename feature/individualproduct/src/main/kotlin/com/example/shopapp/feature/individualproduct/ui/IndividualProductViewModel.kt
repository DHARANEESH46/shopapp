package com.example.shopapp.feature.individualproduct.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.domain.model.CartItem
import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.ResultState
import com.example.shopapp.core.domain.model.WishlistItem
import com.example.shopapp.core.domain.repository.AuthRepository
import com.example.shopapp.core.domain.repository.CartRepository
import com.example.shopapp.core.domain.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IndividualProductViewModel @Inject constructor(
    private val getProductByIdUseCase: com.example.shopapp.core.domain.usecase.GetProductByIdUseCase,
    private val cartRepository: CartRepository,
    private val wishlistRepository: WishlistRepository,
    private val authRepository: AuthRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val productId: Int = checkNotNull(savedStateHandle["productId"])

    private val _productState = MutableStateFlow<ResultState<Product>>(ResultState.Loading)
    val productState: StateFlow<ResultState<Product>> = _productState.asStateFlow()

    private val _cartCount = MutableStateFlow(0)
    val cartCount: StateFlow<Int> = _cartCount.asStateFlow()

    private val _isInWishlist = MutableStateFlow(false)
    val isInWishlist: StateFlow<Boolean> = _isInWishlist.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    init {
        loadProduct()
        observeCartCount()
        observeWishlistStatus()
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
            authRepository.getCurrentUserFlow().collect { user ->
                val uid = user?.id ?: return@collect
                cartRepository.getCartCount(uid).collect {
                    _cartCount.value = it
                }
            }
        }
    }

    private fun observeWishlistStatus() {
        viewModelScope.launch {
            authRepository.getCurrentUserFlow().collect { user ->
                val uid = user?.id ?: return@collect
                wishlistRepository.isInWishlist(uid, productId).collect {
                    _isInWishlist.value = it
                }
            }
        }
    }

    fun toggleWishlist(product: Product) {
        viewModelScope.launch {
            val uid = authRepository.getCurrentUser()?.id ?: return@launch
            if (_isInWishlist.value) {
                wishlistRepository.removeFromWishlist(uid, product.id)
                _snackbarMessage.value = "${product.title} removed from wishlist"
            } else {
                wishlistRepository.addToWishlist(
                    WishlistItem(
                        userId = uid,
                        productId = product.id,
                        productTitle = product.title,
                        productPrice = product.price,
                        productThumbnail = product.thumbnail
                    )
                )
                _snackbarMessage.value = "${product.title} added to wishlist!"
            }
        }
    }

    fun addToCart(product: Product, quantity: Int) {
        viewModelScope.launch {
            val uid = authRepository.getCurrentUser()?.id ?: return@launch
            cartRepository.addToCart(
                CartItem(
                    userId = uid,
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