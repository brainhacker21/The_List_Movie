package com.auric.themoviessub1byauric.di

import com.auric.themoviessub1byauric.androidcore.domain.usecase.TheMoviesInteractor
import com.auric.themoviessub1byauric.androidcore.domain.usecase.TheMoviesUseCase
import com.auric.themoviessub1byauric.ui.details.DetailsMoviesViewModel
import com.auric.themoviessub1byauric.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        DetailsMoviesViewModel(get())
    }
}

    val useCaseModule = module {
        factory<TheMoviesUseCase> {
            TheMoviesInteractor(get())
        }
    }
