package com.auric.themoviessub1byauric.watchlist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.auric.themoviessub1byauric.androidcore.adapter.TheMoviesAdapter
import com.auric.themoviessub1byauric.ui.details.DetailsMoviesActivity
import com.auric.themoviessub1byauric.ui.details.DetailsMoviesActivity.Companion.EXTRA_DATA
import com.auric.themoviessub1byauric.watchlist.databinding.ActivityWatchlistBinding.inflate
import com.auric.themoviessub1byauric.watchlist.databinding.ActivityWatchlistBinding
import com.auric.themoviessub1byauric.watchlist.di.watchlistModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class WatchlistActivity : AppCompatActivity() {
    private lateinit var activityWatchlistBinding: ActivityWatchlistBinding

    private val watchlistViewModel: WatchlistViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityWatchlistBinding = inflate(layoutInflater)
        activityWatchlistBinding.apply {
            setContentView(root)

            loadKoinModules(watchlistModule)

            val theMoviesAdapter = TheMoviesAdapter()

            theMoviesAdapter.onItemClick = { movee ->
                Intent(this@WatchlistActivity, DetailsMoviesActivity::class.java).also {
                    it.putExtra(EXTRA_DATA, movee)
                    startActivity(it)
                }
            }

            watchlistViewModel.moviesWatchlist.observe(this@WatchlistActivity) {
                theMoviesAdapter.setData(it)
                viewEmpty.root.visibility = if (it.isNotEmpty()) GONE else VISIBLE
            }

            rvMovies.apply {
                layoutManager = LinearLayoutManager(this@WatchlistActivity)
                setHasFixedSize(true)
                adapter = theMoviesAdapter
            }
        }
    }

}