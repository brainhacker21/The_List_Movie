package com.auric.themoviessub1byauric.androidcore.source.local

import com.auric.themoviessub1byauric.androidcore.source.local.entity.TheMoviesEntity
import com.auric.themoviessub1byauric.androidcore.source.local.room.TheMoviesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val theMoviesDao: TheMoviesDao) {

    fun getList(): Flow<List<TheMoviesEntity>> = theMoviesDao.getList()

    fun getWatchlist(): Flow<List<TheMoviesEntity>> = theMoviesDao.getWatchlist()

    suspend fun insertWatchlist(list: List<TheMoviesEntity>) = theMoviesDao.insertWatchlist(list)

    fun setWatchlist(theMoviesEntity: TheMoviesEntity, newState: Boolean) {
        theMoviesEntity.watchlist = newState
            theMoviesDao.updateWatchlist(theMoviesEntity)
    }
}