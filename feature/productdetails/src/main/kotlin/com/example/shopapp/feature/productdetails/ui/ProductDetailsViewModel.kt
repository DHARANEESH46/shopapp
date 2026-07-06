package com.example.shopapp.feature.productdetails.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.ResultState
import com.example.shopapp.core.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _productsState = MutableStateFlow<ResultState<List<Product>>>(ResultState.Loading)
    val productsState: StateFlow<ResultState<List<Product>>> = _productsState.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            getProductsUseCase().collect { state ->
                _productsState.value = state
            }
        }
    }
}