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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val cartRepository: CartRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var allProducts: List<Product> = emptyList()

    init {
        loadProducts()
        observeCartCount()
    }

    fun loadProducts() {
        viewModelScope.launch {
            getProductsUseCase().collect { state ->
                when (state) {
                    is ResultState.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is ResultState.Success -> {
                        allProducts = state.data
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                products = state.data,
                                filteredProducts = applyFilter(state.data, it.searchQuery)
                            )
                        }
                    }
                    is ResultState.Error -> _uiState.update { it.copy(isLoading = false, errorMessage = state.message) }
                }
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update {
            it.copy(
                searchQuery = query,
                filteredProducts = applyFilter(allProducts, query)
            )
        }
    }

    private fun applyFilter(products: List<Product>, query: String): List<Product> {
        return if (query.isBlank()) products
        else products.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.category.contains(query, ignoreCase = true)
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
}