package com.example.sneakership.feature.cart.di

import com.example.sneakership.feature.cart.data.CartDataSource
import com.example.sneakership.feature.cart.data.local.CartLocalDataSourceImpl
import com.example.sneakership.feature.cart.data.repository.CartRepositoryImpl
import com.example.sneakership.feature.cart.domain.repository.CartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface CartModule {
    @Binds
    fun bindCartRepository(impl: CartRepositoryImpl) : CartRepository

    @Binds
    fun bindCartLocalDataSource(impl: CartLocalDataSourceImpl): CartDataSource
}