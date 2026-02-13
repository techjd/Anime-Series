package com.techjd.animeseries.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data class representing an Anime with its associated Genres
 * This establishes a one-to-many relationship between Anime and Genre
 */
data class AnimeWithGenres(
    @Embedded val anime: AnimeEntity,
    @Relation(
        parentColumn = "animeId",
        entityColumn = "animeId"
    )
    val genres: List<GenreEntity>
)

