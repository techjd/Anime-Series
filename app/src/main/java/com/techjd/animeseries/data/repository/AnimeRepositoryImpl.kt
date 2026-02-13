package com.techjd.animeseries.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.techjd.animeseries.data.local.AnimeDatabase
import com.techjd.animeseries.data.local.entity.AnimeCastEntity
import com.techjd.animeseries.data.mapper.toDomain
import com.techjd.animeseries.data.mapper.toEntity
import com.techjd.animeseries.data.remote.api.JikanApi
import com.techjd.animeseries.data.remote.dto.animecast.AnimeCastResponseDto
import com.techjd.animeseries.data.remote.paging.AnimeRemoteMediator
import com.techjd.animeseries.domain.AnimeRepository
import com.techjd.animeseries.domain.Result
import com.techjd.animeseries.domain.models.Anime
import com.techjd.animeseries.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val api: JikanApi,
    private val db: AnimeDatabase
): AnimeRepository {
    override fun getAnimeDetails(animeId: Int): Flow<Anime> = db
        .animeDao().getAnimeWithDetails(
            animeId
        ).mapNotNull { animeWithDetails ->
            animeWithDetails?.toDomain()
        }

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopAnime(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                initialLoadSize = Constants.PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = 2,
            ),
            remoteMediator = AnimeRemoteMediator(
                animeDatabase = db,
                animeApi = api
            ),
            pagingSourceFactory = { db.animeDao().pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun getAnimeCast(animeId: Int): Result<Any> {
        return try {
            val data = api.getAnimeCast(animeId)
            val castList = data.data?.mapNotNull { it.toEntity(animeId) } ?: emptyList()
            db.animeDao().insertCastList(castList)
            Result.Success(Any())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}