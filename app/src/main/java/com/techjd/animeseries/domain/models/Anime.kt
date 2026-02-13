package com.techjd.animeseries.domain.models

data class Anime(
    val animeId: Int,
    val title: String,
    val titleJapanese: String,
    val synopsis: String,
    val numberOfEpisodes: Int,
    val posterUrl: String,
    val rating: String,
    val videoId: String?,
    val score: Double?,
    val genres: List<Genre>,
    val cast: List<AnimeCast> = emptyList()
)