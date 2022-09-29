package com.auric.themoviessub1byauric.androidcore.source

import com.auric.themoviessub1byauric.androidcore.domain.model.TheMovies
import com.auric.themoviessub1byauric.androidcore.domain.repository.ITheMoviesRepository
import com.auric.themoviessub1byauric.androidcore.source.local.LocalDataSource
import com.auric.themoviessub1byauric.androidcore.source.remote.RemoteDataSource
import com.auric.themoviessub1byauric.androidcore.source.remote.network.APIResponse
import com.auric.themoviessub1byauric.androidcore.source.remote.response.Response
import com.auric.themoviessub1byauric.androidcore.utils.AppExecutors
import com.auric.themoviessub1byauric.androidcore.utils.DataMapper.mapDomainToEntity
import com.auric.themoviessub1byauric.androidcore.utils.DataMapper.mapEntitiesToDomain
import com.auric.themoviessub1byauric.androidcore.utils.DataMapper.mapResponseToEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TheMoviesRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors) :
    ITheMoviesRepository {

    override fun getList(): Flow<Resource<List<TheMovies>>> {
        return object : NetworkBoundResource<List<TheMovies>, List<Response>>() {
            override fun loadFromDB(): Flow<List<TheMovies>> {
                return localDataSource.getList().map {
                    mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TheMovies>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<APIResponse<List<Response>>> = remoteDataSource.getList()

            override suspend fun saveCallResult(data: List<Response>) {
                mapResponseToEntities(data).also {
                    localDataSource.insertWatchlist(it)
                }
            }
        }.asFlow()
    }

    override fun getWatchlist(): Flow<List<TheMovies>> {
        return localDataSource.getWatchlist().map { mapEntitiesToDomain(it) }
    }

    override fun setWatchlist(theMovies: TheMovies, state: Boolean) {
        mapDomainToEntity(theMovies).also {
            appExecutors.diskIO().execute {
                localDataSource.setWatchlist(it, state)
            }
        }
    }

}