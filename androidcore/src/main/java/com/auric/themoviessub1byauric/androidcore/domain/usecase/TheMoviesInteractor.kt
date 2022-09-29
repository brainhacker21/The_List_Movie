package com.auric.themoviessub1byauric.androidcore.domain.usecase

import com.auric.themoviessub1byauric.androidcore.domain.model.TheMovies
import com.auric.themoviessub1byauric.androidcore.domain.repository.ITheMoviesRepository

class TheMoviesInteractor(private val iTheMoviesRepository: ITheMoviesRepository) :
    TheMoviesUseCase {

    override fun getList() = iTheMoviesRepository.getList()

    override fun getWatchlist() = iTheMoviesRepository.getWatchlist()

    override fun setWatchlist(theMovies: TheMovies, state: Boolean) = iTheMoviesRepository.setWatchlist(theMovies, state)

}