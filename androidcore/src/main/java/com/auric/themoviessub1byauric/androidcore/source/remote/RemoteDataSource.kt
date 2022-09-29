package com.auric.themoviessub1byauric.androidcore.source.remote

import android.util.Log
import com.auric.themoviessub1byauric.androidcore.Constant.TAG
import com.auric.themoviessub1byauric.androidcore.source.remote.network.APIResponse
import com.auric.themoviessub1byauric.androidcore.source.remote.network.APIResponse.*
import com.auric.themoviessub1byauric.androidcore.source.remote.network.TMDBService
import com.auric.themoviessub1byauric.androidcore.source.remote.response.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource(private val tmdbService: TMDBService) {

    suspend fun getList(): Flow<APIResponse<List<Response>>> {
        return flow {
            try {
                tmdbService.getMovieNowPlaying().also {
                    it.results.apply {
                        if (isNotEmpty()) {
                            emit(APIResponse.Success(this))
                        } else {
                            emit(APIResponse.Empty)
                        }
                    }
                }
            } catch (e: Exception) {
                e.apply {
                    emit(Error(toString()))
                    Log.e(TAG, toString())
                }
            }
        }.flowOn(Dispatchers.IO)
    }

}