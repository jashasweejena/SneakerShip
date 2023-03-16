package com.example.sneakership.feature.home.di

import com.example.sneakership.feature.home.data.repository.SneakerRepositoryImpl
import com.example.sneakership.feature.home.domain.repository.SneakerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface SneakersModule {
    @Binds
    fun bindSneakerRepository(sneakerRepositoryImpl: SneakerRepositoryImpl): SneakerRepository
}