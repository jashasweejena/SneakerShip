package com.example.sneakership.di

import android.app.Application
import com.example.sneakership.data.local.SneakerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ) = SneakerDatabase.getDatabase(app)

    @Provides
    fun provideCartDao(db: SneakerDatabase) = db.cartDao()
}