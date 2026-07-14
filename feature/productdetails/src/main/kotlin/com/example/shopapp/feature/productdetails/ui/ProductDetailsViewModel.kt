package com.example.shopapp.feature.productdetails.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.domain.model.ResultState
import com.example.shopapp.core.domain.model.SortOption
import com.example.shopapp.core.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PAGE_SIZE = 10

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailsUiState())
    val uiState: StateFlow<ProductDetailsUiState> = _uiState.asStateFlow()

    init {
        loadFirstPage()
    }

    private fun loadFirstPage() {
        _uiState.update {
            it.copy(
                products = emptyList(),
                currentSkip = 0,
                hasReachedEnd = false,
                isLoading = true,
                errorMessage = null
            )
        }
        fetchPage(skip = 0)
    }

    fun loadNextPage() {
        val state = _uiState.value
        if (state.isLoadingMore || state.hasReachedEnd) return
        _uiState.update { it.copy(isLoadingMore = true) }
        fetchPage(skip = state.currentSkip)
    }

    private fun fetchPage(skip: Int) {
        val sort = _uiState.value.selectedSort
        viewModelScope.launch {
            getProductsUseCase(
                limit = PAGE_SIZE,
                skip = skip,
                sortBy = sort.sortBy,
                order = sort.order
            ).collect { state ->
                when (state) {
                    is ResultState.Loading -> { /* already set above */ }
                    is ResultState.Success -> {
                        val newItems = state.data
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isLoadingMore = false,
                                products = it.products + newItems,
                                currentSkip = it.currentSkip + newItems.size,
                                hasReachedEnd = newItems.size < PAGE_SIZE
                            )
                        }
                    }
                    is ResultState.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isLoadingMore = false,
                                errorMessage = state.message
                            )
                        }
                    }
                }
            }
        }
    }

    fun onSortSelected(sort: SortOption) {
        _uiState.update {
            it.copy(selectedSort = sort, showSortSheet = false)
        }
        loadFirstPage()
    }

    fun showSortSheet() { _uiState.update { it.copy(showSortSheet = true) } }
    fun hideSortSheet() { _uiState.update { it.copy(showSortSheet = false) } }
}