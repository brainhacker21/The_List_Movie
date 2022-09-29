package com.auric.themoviessub1byauric.androidcore.source.remote.network

import com.auric.themoviessub1byauric.androidcore.BuildConfig.TMDB_API_KEY
import com.auric.themoviessub1byauric.androidcore.source.remote.response.ListResponse
import retrofit2.http.GET

interface TMDBService {

    @GET("movie/now_playing?api_key=$TMDB_API_KEY")
    suspend fun getMovieNowPlaying(): ListResponse

}