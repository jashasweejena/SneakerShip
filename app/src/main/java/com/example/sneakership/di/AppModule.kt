package com.example.sneakership.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.sneakership.data.local.SneakerDatabase
import com.example.sneakership.utils.network.IOnlineChecker
import com.example.sneakership.utils.network.OnlineChecker
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

    @Provides
    fun provideSneakerDao(db: SneakerDatabase) = db.sneakerDao()

    @Singleton
    @Provides
    fun provideConnectivityManager(application: Application): ConnectivityManager {
        return application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    @Provides
    @Singleton
    fun provideOnlineChecker(
        connectivityManager: ConnectivityManager
    ): IOnlineChecker {
        return OnlineChecker(connectivityManager)
    }

//    @Provides
//    @Singleton
//    fun provideResponseHandler(
//    ): ResponseHandler {
//        return ResponseHandler()
//    }
}