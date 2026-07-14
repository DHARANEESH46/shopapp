package com.example.shopapp.core.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.shopapp.core.database.dao.ProductDao
import com.example.shopapp.core.database.mapper.toDomain
import com.example.shopapp.core.database.mapper.toEntity
import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.ResultState
import com.example.shopapp.core.domain.repository.ProductRepository
import com.example.shopapp.core.network.api.ShopApi
import com.example.shopapp.core.network.mapper.toDomain
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val shopApi: ShopApi,
    private val productDao: ProductDao,
    @ApplicationContext private val context: Context
) : ProductRepository {

    private fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val capabilities = cm.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    override fun getProducts(
        limit: Int,
        skip: Int,
        sortBy: String,
        order: String
    ): Flow<ResultState<List<Product>>> = flow {
        emit(ResultState.Loading)
        if (!isOnline()) {
            emit(ResultState.Error("No internet connection"))
            return@flow
        }
        try {
            val response = shopApi.getProducts(limit, skip, sortBy, order)
            emit(ResultState.Success(response.products.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(ResultState.Error(e.message ?: "Unknown error"))
        }
    }

    override fun getProductById(id: Int): Flow<ResultState<Product>> = flow {
        emit(ResultState.Loading)
        val cached = productDao.getProductById(id).first()
        if (cached != null) emit(ResultState.Success(cached.toDomain()))
        if (isOnline()) {
            try {
                val dto = shopApi.getProductById(id)
                val product = dto.toDomain()
                productDao.insertProduct(product.toEntity())
                emit(ResultState.Success(product))
            } catch (e: Exception) {
                if (cached == null) emit(ResultState.Error(e.message ?: "Unknown error"))
            }
        } else {
            if (cached == null) emit(ResultState.Error("No internet connection"))
        }
    }
}