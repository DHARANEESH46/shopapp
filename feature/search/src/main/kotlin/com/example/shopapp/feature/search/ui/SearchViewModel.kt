package com.example.shopapp.feature.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.ResultState
import com.example.shopapp.core.domain.usecase.GetFeaturedProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getFeaturedProductsUseCase: GetFeaturedProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    // Keep full product list in memory for instant filtering
    private var allProducts: List<Product> = emptyList()

    init {
        loadAllProducts()
    }

    private fun loadAllProducts() {
        viewModelScope.launch {
            getFeaturedProductsUseCase().collect { state ->
                if (state is ResultState.Success) {
                    allProducts = state.data
                }
            }
        }
    }

    fun onQueryChange(query: String) {
        val suggestions = if (query.isBlank()) {
            emptyList()
        } else {
            allProducts.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.category.contains(query, ignoreCase = true)
            }.take(8)   // show max 8 suggestions
        }
        _uiState.update { it.copy(query = query, suggestions = suggestions) }
    }
}