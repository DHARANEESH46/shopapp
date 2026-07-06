package com.example.shopapp.core.domain.usecase

import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.ResultState
import com.example.shopapp.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(id: Int): Flow<ResultState<Product>> {
        return productRepository.getProductById(id)
    }
}