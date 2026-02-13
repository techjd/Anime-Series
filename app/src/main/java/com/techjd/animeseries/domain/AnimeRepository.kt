package com.techjd.animeseries.domain

import androidx.paging.PagingData
import com.techjd.animeseries.domain.models.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getAnimeDetails(animeId: Int): Flow<Anime>

    fun getTopAnime(): Flow<PagingData<Anime>>

    suspend fun getAnimeCast(animeId: Int) : Result<Any>
}