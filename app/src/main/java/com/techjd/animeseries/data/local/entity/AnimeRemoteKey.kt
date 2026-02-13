package com.techjd.animeseries.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_remote_keys")
data class AnimeRemoteKey(
    @PrimaryKey val animeId: Int,
    val nextKey: Int?
)