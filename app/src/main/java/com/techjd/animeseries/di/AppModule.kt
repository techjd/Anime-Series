package com.techjd.animeseries.di

import android.content.Context
import com.techjd.animeseries.utils.AndroidConnectivityObserver
import com.techjd.animeseries.utils.ConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesConnectivityObserver(
        @ApplicationContext context: Context
    ): ConnectivityObserver {
        return AndroidConnectivityObserver(context)
    }
}