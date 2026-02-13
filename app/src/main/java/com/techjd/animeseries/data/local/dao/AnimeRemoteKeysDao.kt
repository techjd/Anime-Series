package com.techjd.animeseries.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techjd.animeseries.data.local.entity.AnimeRemoteKey

@Dao
interface AnimeRemoteKeysDao {

    @Query("SELECT * FROM anime_remote_keys WHERE animeId = :animeId")
    suspend fun remoteKeysAnimeId(animeId: Int): AnimeRemoteKey?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(keys: List<AnimeRemoteKey>)

    @Query("DELETE FROM anime_remote_keys")
    suspend fun clearRemoteKeys()
}