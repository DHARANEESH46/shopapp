package com.example.shopapp.core.domain.usecase

import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.ResultState
import com.example.shopapp.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(
        limit: Int = 10,
        skip: Int = 0,
        sortBy: String = "title",
        order: String = "asc"
    ): Flow<ResultState<List<Product>>> {
        return repository.getProducts(limit, skip, sortBy, order)
    }
}