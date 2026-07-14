package com.example.shopapp.core.network.api

import com.example.shopapp.core.network.dto.LoginRequestDto
import com.example.shopapp.core.network.dto.LoginResponseDto
import com.example.shopapp.core.network.dto.ProductDto
import com.example.shopapp.core.network.dto.ProductsResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopApi {

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int = 10,
        @Query("skip") skip: Int = 0,
        @Query("sortBy") sortBy: String = "title",
        @Query("order") order: String = "asc"
    ): ProductsResponseDto

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String
    ): ProductsResponseDto

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequestDto): LoginResponseDto
}