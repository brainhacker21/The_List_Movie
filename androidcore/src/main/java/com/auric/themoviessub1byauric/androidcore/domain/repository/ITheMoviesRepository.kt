package com.auric.themoviessub1byauric.androidcore.domain.repository

import com.auric.themoviessub1byauric.androidcore.domain.model.TheMovies
import com.auric.themoviessub1byauric.androidcore.source.Resource
import kotlinx.coroutines.flow.Flow

interface ITheMoviesRepository {

    fun getList(): Flow<Resource<List<TheMovies>>>

    fun getWatchlist(): Flow<List<TheMovies>>

    fun setWatchlist(theMovies: TheMovies, state: Boolean)

}