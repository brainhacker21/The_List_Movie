package com.auric.themoviessub1byauric.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.auric.themoviessub1byauric.R
import com.auric.themoviessub1byauric.androidcore.Constant.WATCHLIST_URI
import com.auric.themoviessub1byauric.androidcore.adapter.TheMoviesAdapter
import com.auric.themoviessub1byauric.androidcore.source.Resource
import com.auric.themoviessub1byauric.databinding.ActivityMainBinding
import com.auric.themoviessub1byauric.databinding.ActivityMainBinding.inflate
import com.auric.themoviessub1byauric.ui.details.DetailsMoviesActivity
import com.auric.themoviessub1byauric.ui.details.DetailsMoviesActivity.Companion.EXTRA_DATA
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var theMoviesAdapter: TheMoviesAdapter

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = inflate(layoutInflater)
        activityMainBinding.apply {
            setContentView(root)

            theMoviesAdapter = TheMoviesAdapter()
            theMoviesAdapter.onItemClick = { theMovies->
                Intent(this@MainActivity, DetailsMoviesActivity::class.java).also {
                    it.putExtra(EXTRA_DATA, theMovies)
                    startActivity(it)
                }
            }

            rvThemovies.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = theMoviesAdapter
            }



            mainViewModel.theMovies.observe(this@MainActivity) {
                it.apply {
                    if (this != null) {
                        when (this) {
                            is Resource.Loading -> {
                                viewLoading(true)
                            }
                            is Resource.Success -> {
                                viewLoading(false)
                                theMoviesAdapter.setData(data)
                            }
                            is Resource.Error -> {
                                viewLoading(false)
                                viewError.apply {
                                    root.visibility = android.view.View.GONE
                                    tvError.text = message
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_watchlist, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_watchlist -> {
              val uri = Uri.parse(WATCHLIST_URI)
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
            }
        return true
        }

    private fun viewLoading(state: Boolean) {
        activityMainBinding.loading.visibility = if (state) VISIBLE else GONE
    }
}