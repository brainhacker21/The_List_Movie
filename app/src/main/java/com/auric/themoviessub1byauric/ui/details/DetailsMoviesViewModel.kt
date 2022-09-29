package com.auric.themoviessub1byauric.ui.details

import androidx.lifecycle.ViewModel
import com.auric.themoviessub1byauric.androidcore.domain.model.TheMovies
import com.auric.themoviessub1byauric.androidcore.domain.usecase.TheMoviesUseCase

class DetailsMoviesViewModel(private val theMoviesUseCase: TheMoviesUseCase) : ViewModel() {

    fun watchlist(theMovies: TheMovies, newState: Boolean) = theMoviesUseCase.setWatchlist(theMovies, newState)

}