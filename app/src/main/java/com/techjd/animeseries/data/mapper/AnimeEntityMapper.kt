package com.techjd.animeseries.data.mapper

import com.techjd.animeseries.data.local.entity.AnimeEntity
import com.techjd.animeseries.domain.models.Anime

fun AnimeEntity.toDomain(): Anime = Anime(
    animeId = animeId,
    title = title,
    titleJapanese = titleJapanese,
    synopsis = synopsis.orEmpty(),
    numberOfEpisodes = numberOfEpisodes ?: 0,
    posterUrl = posterUrl.orEmpty(),
    rating = rating.orEmpty(),
    videoId = trailerId,
    score = score,
    genres = emptyList()
)