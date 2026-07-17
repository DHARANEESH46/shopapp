package com.example.shopapp.feature.productdetails.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.SortOption
import com.example.shopapp.core.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailsUiState())
    val uiState: StateFlow<ProductDetailsUiState> = _uiState.asStateFlow()

    private val _sortOption = MutableStateFlow(SortOption.NAME_A_Z)

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagedProducts: Flow<PagingData<Product>> = _sortOption
        .flatMapLatest { sort ->
            getProductsUseCase(sort)
        }
        .cachedIn(viewModelScope)

    fun onSortSelected(sort: SortOption) {
        _sortOption.value = sort
        _uiState.update { it.copy(selectedSort = sort, showSortSheet = false) }
    }

    fun showSortSheet() { _uiState.update { it.copy(showSortSheet = true) } }
    fun hideSortSheet() { _uiState.update { it.copy(showSortSheet = false) } }
}