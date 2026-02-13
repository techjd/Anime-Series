package com.techjd.animeseries.data.remote.api

import com.techjd.animeseries.data.remote.dto.animecast.AnimeCastResponseDto
import com.techjd.animeseries.data.remote.dto.animelist.AnimeListResponseDto
import com.techjd.animeseries.data.remote.dto.singleanime.AnimeResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitJikanApi {
    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int = 1
    ): AnimeListResponseDto

    @GET("anime/{anime_id}")
    suspend fun getAnimeDetails(
        @Path("anime_id") animeId: Int
    ): AnimeResponseDto

    @GET("anime/{anime_id}/characters")
    suspend fun getAnimeCharacters(
        @Path("anime_id") animeId: Int
    ) : AnimeCastResponseDto
}
