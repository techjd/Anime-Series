package com.techjd.animeseries.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.techjd.animeseries.data.local.entity.AnimeCastEntity
import com.techjd.animeseries.data.local.entity.AnimeEntity
import com.techjd.animeseries.data.local.entity.AnimeWithCast
import com.techjd.animeseries.data.local.entity.AnimeWithDetails
import com.techjd.animeseries.data.local.entity.AnimeWithGenres
import com.techjd.animeseries.data.local.entity.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime ORDER BY `order` ASC")
    fun pagingSource(): PagingSource<Int, AnimeEntity>
    /**
     * Insert multiple anime
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimes(animes: List<AnimeEntity>)

    /**
     * Clear all anime from cache (genres and anime_cast will be cascade deleted)
     */
    @Query("DELETE FROM anime")
    suspend fun clearAll()

    /**
     * Insert multiple genres
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenreEntity>)

    /**
     * Insert multiple anime_cast members
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCastList(castList: List<AnimeCastEntity>)

    /**
     * Clear all anime_cast
     */
    @Query("DELETE FROM anime_cast")
    suspend fun clearAllCast()

    /**
     * Get a specific anime with complete details (genres and anime_cast) by ID
     */
    @Transaction
    @Query("SELECT * FROM anime WHERE animeId = :animeId")
    fun getAnimeWithDetails(animeId: Int): Flow<AnimeWithDetails?>
}
