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
import com.example.shopapp.core.domain.usecase.GetProductByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IndividualProductViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val cartRepository: CartRepository,
    private val wishlistRepository: WishlistRepository,
    private val authRepository: AuthRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val productId: Int = checkNotNull(savedStateHandle["productId"])

    private val _uiState = MutableStateFlow(IndividualProductUiState())
    val uiState: StateFlow<IndividualProductUiState> = _uiState.asStateFlow()

    init {
        loadProduct()
        observeCartCount()
        observeWishlistStatus()
    }

    private fun loadProduct() {
        viewModelScope.launch {
            getProductByIdUseCase(productId).collect { state ->
                when (state) {
                    is ResultState.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is ResultState.Success -> _uiState.update { it.copy(isLoading = false, product = state.data) }
                    is ResultState.Error -> _uiState.update { it.copy(isLoading = false, errorMessage = state.message) }
                }
            }
        }
    }

    private fun observeCartCount() {
        viewModelScope.launch {
            authRepository.getCurrentUserFlow().collect { user ->
                val uid = user?.id ?: return@collect
                cartRepository.getCartCount(uid).collect { count ->
                    _uiState.update { it.copy(cartCount = count) }
                }
            }
        }
    }

    private fun observeWishlistStatus() {
        viewModelScope.launch {
            authRepository.getCurrentUserFlow().collect { user ->
                val uid = user?.id ?: return@collect
                wishlistRepository.isInWishlist(uid, productId).collect { inWishlist ->
                    _uiState.update { it.copy(isInWishlist = inWishlist) }
                }
            }
        }
    }

    fun toggleWishlist(product: Product) {
        viewModelScope.launch {
            val uid = authRepository.getCurrentUser()?.id ?: return@launch
            if (_uiState.value.isInWishlist) {
                wishlistRepository.removeFromWishlist(uid, product.id)
                _uiState.update { it.copy(snackbarMessage = "${product.title} removed from wishlist") }
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
                _uiState.update { it.copy(snackbarMessage = "${product.title} added to wishlist!") }
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
            _uiState.update { it.copy(snackbarMessage = "${product.title} added to cart!") }
        }
    }

    fun clearSnackbar() {
        _uiState.update { it.copy(snackbarMessage = null) }
    }
}