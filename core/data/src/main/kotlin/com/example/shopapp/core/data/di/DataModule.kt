package com.example.shopapp.core.data.di

import com.example.shopapp.core.data.repository.AuthRepositoryImpl
import com.example.shopapp.core.data.repository.CartRepositoryImpl
import com.example.shopapp.core.data.repository.ProductRepositoryImpl
import com.example.shopapp.core.domain.repository.AuthRepository
import com.example.shopapp.core.domain.repository.CartRepository
import com.example.shopapp.core.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds @Singleton
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds @Singleton
    abstract fun bindCartRepository(impl: CartRepositoryImpl): CartRepository
}