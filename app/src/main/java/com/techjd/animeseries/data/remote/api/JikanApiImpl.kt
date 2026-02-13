package com.techjd.animeseries.data.remote.api

import com.techjd.animeseries.data.remote.dto.animecast.AnimeCastResponseDto
import com.techjd.animeseries.data.remote.dto.animelist.AnimeListResponseDto
import com.techjd.animeseries.data.remote.dto.singleanime.AnimeResponseDto
import javax.inject.Inject

class JikanApiImpl @Inject constructor(
    private val retrofitJikanApi: RetrofitJikanApi
) : JikanApi {
    override suspend fun getTopAnime(page: Int): AnimeListResponseDto {
        return retrofitJikanApi.getTopAnime(page)
    }

    override suspend fun getAnimeDetails(animeId: Int): AnimeResponseDto {
        return retrofitJikanApi.getAnimeDetails(animeId)
    }

    override suspend fun getAnimeCast(animeId: Int): AnimeCastResponseDto {
        return retrofitJikanApi.getAnimeCharacters(animeId)
    }
}