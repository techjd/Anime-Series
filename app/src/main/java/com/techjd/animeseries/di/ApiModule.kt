package com.techjd.animeseries.di

import com.techjd.animeseries.data.remote.api.JikanApi
import com.techjd.animeseries.data.remote.api.JikanApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    @Binds
    @Singleton
    abstract fun bindJikanApi(
        jikanApiImpl: JikanApiImpl
    ): JikanApi
}