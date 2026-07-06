package com.example.shopapp.core.domain.repository

import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.ResultState
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<ResultState<List<Product>>>
    fun getProductById(id: Int): Flow<ResultState<Product>>
    fun getProductsByCategory(category: String): Flow<ResultState<List<Product>>>
}