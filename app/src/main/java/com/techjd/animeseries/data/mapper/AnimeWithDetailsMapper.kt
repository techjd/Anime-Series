package com.techjd.animeseries.data.mapper

import com.techjd.animeseries.data.local.entity.AnimeCastEntity
import com.techjd.animeseries.data.local.entity.AnimeWithDetails
import com.techjd.animeseries.data.local.entity.GenreEntity
import com.techjd.animeseries.domain.models.Anime
import com.techjd.animeseries.domain.models.AnimeCast
import com.techjd.animeseries.domain.models.Genre


fun GenreEntity.toDomain(): Genre = Genre(
    id = genreId,
    name = name,
)

fun AnimeCastEntity.toDomain(): AnimeCast = AnimeCast(
    id = castId,
    name = name,
    imageUrl = imageUrl
)

fun AnimeWithDetails.toDomain(): Anime = Anime(
    animeId = anime.animeId,
    title = anime.title,
    titleJapanese = anime.titleJapanese,
    synopsis = anime.synopsis.orEmpty(),
    numberOfEpisodes = anime.numberOfEpisodes ?: 0,
    posterUrl = anime.posterUrl.orEmpty(),
    rating = anime.rating.orEmpty(),
    videoId = anime.trailerId,
    score = anime.score,
    genres = genres.map { it.toDomain() },
    cast = cast.map { it.toDomain() }
)