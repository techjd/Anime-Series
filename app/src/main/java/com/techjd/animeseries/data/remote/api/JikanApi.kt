package com.techjd.animeseries.data.remote.api

import com.techjd.animeseries.data.remote.dto.animecast.AnimeCastResponseDto
import com.techjd.animeseries.data.remote.dto.animelist.AnimeListResponseDto
import com.techjd.animeseries.data.remote.dto.singleanime.AnimeResponseDto

interface JikanApi {
    suspend fun getTopAnime(page: Int) : AnimeListResponseDto

    suspend fun getAnimeDetails(animeId: Int) : AnimeResponseDto

    suspend fun getAnimeCast(animeId: Int) : AnimeCastResponseDto
}