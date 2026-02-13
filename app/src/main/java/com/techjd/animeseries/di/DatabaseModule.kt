package com.techjd.animeseries.di

import android.content.Context
import androidx.room.Room
import com.techjd.animeseries.data.local.AnimeDatabase
import com.techjd.animeseries.data.local.dao.AnimeDao
import com.techjd.animeseries.data.local.entity.AnimeRemoteKey
import com.techjd.animeseries.data.local.dao.AnimeRemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAnimeDatabase(
        @ApplicationContext context: Context
    ): AnimeDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "anime_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAnimeDao(database: AnimeDatabase): AnimeDao {
        return database.animeDao()
    }

    @Provides
    @Singleton
    fun provideAnimeRemoteKeysDao(database: AnimeDatabase): AnimeRemoteKeysDao {
        return database.animeRemoteKeysDao()
    }
}