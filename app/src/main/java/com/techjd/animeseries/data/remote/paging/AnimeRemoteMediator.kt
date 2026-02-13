package com.techjd.animeseries.data.remote.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.techjd.animeseries.data.local.AnimeDatabase
import com.techjd.animeseries.data.local.entity.AnimeEntity
import com.techjd.animeseries.data.local.entity.AnimeRemoteKey
import com.techjd.animeseries.data.local.entity.GenreEntity
import com.techjd.animeseries.data.remote.api.JikanApi
import com.techjd.animeseries.data.remote.dto.animelist.Images
import com.techjd.animeseries.utils.Constants
import com.techjd.animeseries.utils.extractVideoIdFromYoutubeUrl
import retrofit2.HttpException
import java.io.IOException
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediator(
    private val animeDatabase: AnimeDatabase,
    private val animeApi: JikanApi
) : RemoteMediator<Int, AnimeEntity>() {
    @OptIn(ExperimentalTime::class)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimeEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state, animeDatabase)
                    Log.d("RemoteKey", "load: ${state.pages.lastOrNull()} ${remoteKey}")
                    val nextKey = remoteKey?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    nextKey
                }
            }

            Log.d("Mediator", "loadType=$loadType")

            val topAnime = animeApi.getTopAnime(
                page = page,
            )

            val endReached = topAnime.pagination?.hasNextPage == false
            val nextKey = if (endReached) null else page + 1

            Log.d("Key", "load: ${endReached} - ${nextKey}")

            animeDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    animeDatabase.animeRemoteKeysDao().clearRemoteKeys()
                    animeDatabase.animeDao().clearAll()
                }

                val animeEntities = topAnime.data
                    .mapIndexedNotNull { idx, dto ->

                        val malId = dto.malId ?: return@mapIndexedNotNull null

                        AnimeEntity(
                            animeId = malId,
                            title = dto.title
                                ?: dto.titleEnglish
                                ?: "",
                            posterUrl = dto.images?.bestPoster(),
                            trailerId = extractVideoIdFromYoutubeUrl(dto.trailer?.embedUrl),
                            synopsis = dto.synopsis,
                            numberOfEpisodes = dto.episodes,
                            rating = dto.rating,
                            score = dto.score,
                            titleJapanese = dto.titleJapanese ?: "",
                            order = (page - 1) * Constants.PAGE_SIZE + idx
                        )
                    }

                val genreEntities = topAnime.data.flatMap { dto ->
                    dto.malId?.let { animeId ->
                        dto.genres.mapNotNull { genreDto ->
                            val genreId = genreDto.malId
                            val name = genreDto.name
                            val type = genreDto.type

                            if (genreId != null && name != null && type != null) {
                                GenreEntity(
                                    animeId = animeId,
                                    genreId = genreId,
                                    name = name,
                                    type = type
                                )
                            } else null
                        }
                    } ?: emptyList()
                }

                val keys = topAnime.data.mapNotNull { dto ->
                    val malId = dto.malId ?: return@mapNotNull null

                    AnimeRemoteKey(
                        animeId = malId,
                        nextKey = nextKey
                    )
                }

                animeDatabase.animeRemoteKeysDao().insertAll(keys)
                animeDatabase.animeDao().insertAnimes(animeEntities)
                animeDatabase.animeDao().insertGenres(genreEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = endReached
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, AnimeEntity>,
        db: AnimeDatabase
    ): AnimeRemoteKey? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data
            ?.lastOrNull()
            ?.let { lastItem ->
                Log.d("LastItem", "getRemoteKeyForLastItem: ${lastItem.animeId}")
                animeDatabase.withTransaction {
                        db.animeRemoteKeysDao().remoteKeysAnimeId(lastItem.animeId)
                    }
                }
    }


    private fun Images?.bestPoster(): String? {
        return listOf(
            this?.webp?.largeImageUrl,
            this?.webp?.imageUrl,
            this?.webp?.smallImageUrl,
            this?.jpg?.largeImageUrl,
            this?.jpg?.imageUrl,
            this?.jpg?.smallImageUrl
        ).firstOrNull { !it.isNullOrBlank() }
    }
}