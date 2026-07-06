package com.example.shopapp.core.domain.usecase

import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.ResultState
import com.example.shopapp.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): Flow<ResultState<List<Product>>> {
        return productRepository.getProducts()
    }
}