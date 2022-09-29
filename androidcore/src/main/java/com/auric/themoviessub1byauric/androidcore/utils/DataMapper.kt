package com.auric.themoviessub1byauric.androidcore.utils

import com.auric.themoviessub1byauric.androidcore.domain.model.TheMovies
import com.auric.themoviessub1byauric.androidcore.source.local.entity.TheMoviesEntity
import com.auric.themoviessub1byauric.androidcore.source.remote.response.Response

object DataMapper {

    fun mapResponseToEntities(input: List<Response>): List<TheMoviesEntity> {
        val theMoviesEntity = ArrayList<TheMoviesEntity>()

        input.map { response ->
            response.apply {
                TheMoviesEntity(
                    id = id,
                    backdrop = backdrop,
                    poster = poster,
                    title = title,
                    releaseDate = releaseDate,
                    rating = rating,
                    synopsis = synopsis,
                    watchlist = false
                ).also {
                    theMoviesEntity.add(it)
                }
            }
        }

        return theMoviesEntity
    }

    fun mapEntitiesToDomain(input: List<TheMoviesEntity>): List<TheMovies> {
        return input.map {
            TheMovies(
                id = it.id,
                backdrop = it.backdrop,
                poster = it.poster,
                title = it.title,
                releaseDate = it.releaseDate,
                rating = it.rating,
                synopsis = it.synopsis,
                watchlist = it.watchlist
            )
        }
    }

    fun mapDomainToEntity(input: TheMovies) = TheMoviesEntity(
        id = input.id,
        backdrop = input.backdrop,
        poster = input.poster,
        title = input.title,
        releaseDate = input.releaseDate,
        rating = input.rating,
        synopsis = input.synopsis,
        watchlist = input.watchlist
    )

}