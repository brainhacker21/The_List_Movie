package com.auric.themoviessub1byauric.watchlist.di

import com.auric.themoviessub1byauric.watchlist.ui.WatchlistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val watchlistModule = module {
    viewModel {
        WatchlistViewModel(get())
    }
}