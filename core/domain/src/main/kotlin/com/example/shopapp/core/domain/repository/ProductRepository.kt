package com.example.shopapp.core.domain.repository

import androidx.paging.PagingData
import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.ResultState
import com.example.shopapp.core.domain.model.SortOption
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    // Simple one-shot list — used by Home (search/filter)
    fun getProducts(): Flow<ResultState<List<Product>>>

    // Infinite paging — used by the "All Products" grid screen
    fun getPagedProducts(sortOption: SortOption): Flow<PagingData<Product>>

    fun getProductById(id: Int): Flow<ResultState<Product>>
}