package com.auric.themoviessub1byauric.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.auric.themoviessub1byauric.androidcore.domain.usecase.TheMoviesUseCase

class MainViewModel (theMoviesUseCase: TheMoviesUseCase) : ViewModel() {

    val theMovies = theMoviesUseCase.getList().asLiveData()
}