package com.techjd.animeseries.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey
    val animeId: Int,
    val title: String,
    val titleJapanese: String,
    val posterUrl: String?,
    val trailerId: String?,
    val synopsis: String?,
    val numberOfEpisodes: Int?,
    val rating: String?,
    val score: Double?,
    val order: Int
)
