package com.auric.themoviessub1byauric.androidcore.source.local.room

import androidx.room.*
import com.auric.themoviessub1byauric.androidcore.source.local.entity.TheMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TheMoviesDao {

    @Query("SELECT * FROM theMovies")
    fun getList(): Flow<List<TheMoviesEntity>>

    @Query("SELECT * FROM theMovies WHERE watchlist = 1")
    fun getWatchlist(): Flow<List<TheMoviesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlist(movies: List<TheMoviesEntity>)

    @Update
    fun updateWatchlist(entity: TheMoviesEntity)

}