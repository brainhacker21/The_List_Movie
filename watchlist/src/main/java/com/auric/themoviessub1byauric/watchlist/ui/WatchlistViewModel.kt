package com.auric.themoviessub1byauric.watchlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.auric.themoviessub1byauric.androidcore.domain.usecase.TheMoviesUseCase

class WatchlistViewModel(theMoviesUseCase: TheMoviesUseCase) : ViewModel() {

    val moviesWatchlist = theMoviesUseCase.getWatchlist().asLiveData()

}