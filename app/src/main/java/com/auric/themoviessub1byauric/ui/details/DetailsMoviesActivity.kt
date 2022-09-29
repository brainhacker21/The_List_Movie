package com.auric.themoviessub1byauric.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.auric.themoviessub1byauric.R
import com.auric.themoviessub1byauric.androidcore.Constant.DETAILS_POSTER_RADIUS
import com.auric.themoviessub1byauric.androidcore.Constant.IMAGE_BASE_URL
import com.auric.themoviessub1byauric.androidcore.Constant.RADIUS
import com.auric.themoviessub1byauric.androidcore.Constant.SAMPLING
import com.auric.themoviessub1byauric.androidcore.domain.model.TheMovies
import com.auric.themoviessub1byauric.databinding.ActivityDetailsMoviesBinding
import com.auric.themoviessub1byauric.databinding.ActivityDetailsMoviesBinding.inflate
import com.auric.themoviessub1byauric.databinding.DetailsGuildlineBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.placeholderOf
import jp.wasabeef.glide.transformations.BlurTransformation
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsMoviesActivity : AppCompatActivity() {
    private lateinit var activityDetailsMoviesBinding: ActivityDetailsMoviesBinding
    private lateinit var detailsGuildlineBinding: DetailsGuildlineBinding

    private val detailsViewModel: DetailsMoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailsMoviesBinding = inflate(layoutInflater)
        activityDetailsMoviesBinding.apply {
            setContentView(root)
            detailsGuildlineBinding = details
        }

        intent.getParcelableExtra<TheMovies>(EXTRA_DATA).also {
            showDetails(it)
        }
    }

    private fun showDetails(theMovies: TheMovies?) {
        detailsGuildlineBinding.apply {
            theMovies?.apply {
                Glide
                    .with(this@DetailsMoviesActivity)
                    .load("$IMAGE_BASE_URL$backdrop")
                    .apply(placeholderOf(R.drawable.background_loading_backdrop).error(R.drawable.background_loading_poster))
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(RADIUS, SAMPLING)))
                    .into(ivBackdrop)
                Glide
                    .with(this@DetailsMoviesActivity)
                    .load("$IMAGE_BASE_URL$poster")
                    .apply(placeholderOf(R.drawable.background_loading_poster).error(R.drawable.background_loading_poster))
                    .transform(RoundedCorners(DETAILS_POSTER_RADIUS))
                    .into(ivPoster)
                tvTitle.text = title
                tvRating.text = rating.toString()
                tvReleaseDate.text = releaseDate
                tvSynopsis.text = synopsis

                var watchlistStatus = watchlist

                setWatchlistStatus(watchlistStatus)
                buttonWatchlist.setOnClickListener {
                    watchlistStatus = !watchlistStatus
                    detailsViewModel.watchlist(this, watchlistStatus)
                    setWatchlistStatus(watchlistStatus)
                }
            }
        }
    }

    private fun setWatchlistStatus(watchlistStatus: Boolean) {
        detailsGuildlineBinding.buttonWatchlist.apply {
            if (watchlistStatus) {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DetailsMoviesActivity,
                        R.drawable.ic_watchlist_true
                    )
                )
            } else {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DetailsMoviesActivity,
                        R.drawable.ic_watchlist_false
                    )
                )
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

}