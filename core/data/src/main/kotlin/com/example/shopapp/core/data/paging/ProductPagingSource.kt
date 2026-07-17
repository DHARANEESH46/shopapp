package com.example.shopapp.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.SortOption
import com.example.shopapp.core.network.api.ShopApi
import com.example.shopapp.core.network.mapper.toDomain

class ProductPagingSource(
    private val shopApi: ShopApi,
    private val sortOption: SortOption
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val skip = params.key ?: 0
        return try {
            val response = shopApi.getProducts(
                limit = params.loadSize,
                skip = skip,
                sortBy = sortOption.sortBy,
                order = sortOption.order
            )
            val products = response.products.map { it.toDomain() }
            LoadResult.Page(
                data = products,
                prevKey = if (skip == 0) null else skip - params.loadSize,
                nextKey = if (products.isEmpty()) null else skip + products.size
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(state.config.pageSize)
        }
    }
}