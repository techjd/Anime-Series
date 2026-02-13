package com.techjd.animeseries.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data class representing an Anime with all its associated data
 * This establishes relationships with both Genres and Cast
 */
data class AnimeWithDetails(
    @Embedded val anime: AnimeEntity,
    @Relation(
        parentColumn = "animeId",
        entityColumn = "animeId"
    )
    val genres: List<GenreEntity>,
    @Relation(
        parentColumn = "animeId",
        entityColumn = "animeId"
    )
    val cast: List<AnimeCastEntity>
)

