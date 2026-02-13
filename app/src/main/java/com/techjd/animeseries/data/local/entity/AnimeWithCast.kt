package com.techjd.animeseries.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data class representing an Anime with its associated Cast members
 * This establishes a one-to-many relationship between Anime and Cast
 */
data class AnimeWithCast(
    @Embedded val anime: AnimeEntity,
    @Relation(
        parentColumn = "animeId",
        entityColumn = "animeId"
    )
    val cast: List<AnimeCastEntity>
)
