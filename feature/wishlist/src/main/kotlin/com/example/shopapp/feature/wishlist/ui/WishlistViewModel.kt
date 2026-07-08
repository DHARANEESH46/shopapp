package com.example.shopapp.feature.wishlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.domain.model.WishlistItem
import com.example.shopapp.core.domain.repository.AuthRepository
import com.example.shopapp.core.domain.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val wishlistRepository: WishlistRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _wishlistItems = MutableStateFlow<List<WishlistItem>>(emptyList())
    val wishlistItems: StateFlow<List<WishlistItem>> = _wishlistItems.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.getCurrentUserFlow().collect { user ->
                val uid = user?.id ?: return@collect
                wishlistRepository.getWishlist(uid).collect { items ->
                    _wishlistItems.value = items
                }
            }
        }
    }

    fun removeFromWishlist(productId: Int) {
        viewModelScope.launch {
            val uid = authRepository.getCurrentUser()?.id ?: return@launch
            wishlistRepository.removeFromWishlist(uid, productId)
        }
    }
}