package com.techjd.animeseries.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techjd.animeseries.data.local.dao.AnimeDao
import com.techjd.animeseries.data.local.entity.AnimeCastEntity
import com.techjd.animeseries.data.local.entity.AnimeEntity
import com.techjd.animeseries.data.local.entity.AnimeRemoteKey
import com.techjd.animeseries.data.local.dao.AnimeRemoteKeysDao
import com.techjd.animeseries.data.local.entity.GenreEntity

@Database(
    entities = [AnimeEntity::class, GenreEntity::class, AnimeCastEntity::class, AnimeRemoteKey::class],
    version = 1,
    exportSchema = false
)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao

    abstract fun animeRemoteKeysDao(): AnimeRemoteKeysDao
}
