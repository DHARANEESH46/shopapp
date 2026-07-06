package com.example.shopapp.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.ResultState
import com.example.shopapp.core.domain.repository.AuthRepository
import com.example.shopapp.core.domain.repository.CartRepository
import com.example.shopapp.core.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val cartRepository: CartRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _productsState = MutableStateFlow<ResultState<List<Product>>>(ResultState.Loading)
    val productsState: StateFlow<ResultState<List<Product>>> = _productsState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts.asStateFlow()

    private var allProducts: List<Product> = emptyList()

    private val userId: Int get() = authRepository.getCurrentUser()?.id ?: 0

    private val _cartCount = MutableStateFlow(0)
    val cartCount: StateFlow<Int> = _cartCount.asStateFlow()

    init {
        loadProducts()
        observeCartCount()
    }

    fun loadProducts() {
        viewModelScope.launch {
            getProductsUseCase().collect { state ->
                _productsState.value = state
                if (state is ResultState.Success) {
                    allProducts = state.data
                    filterProducts(_searchQuery.value)
                }
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        filterProducts(query)
    }

    private fun filterProducts(query: String) {
        _filteredProducts.value = if (query.isBlank()) {
            allProducts
        } else {
            allProducts.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.category.contains(query, ignoreCase = true)
            }
        }
    }

    private fun observeCartCount() {
        viewModelScope.launch {
            cartRepository.getCartCount(userId).collect {
                _cartCount.value = it
            }
        }
    }

}