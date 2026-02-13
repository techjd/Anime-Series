package com.techjd.animeseries.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "anime_cast",
    foreignKeys = [
        ForeignKey(
            entity = AnimeEntity::class,
            parentColumns = ["animeId"],
            childColumns = ["animeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["animeId"])]
)
data class AnimeCastEntity(
    @PrimaryKey
    val castId: Int,
    val animeId: Int,
    val name: String,
    val imageUrl: String
)
