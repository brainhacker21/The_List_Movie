package com.auric.themoviessub1byauric.androidcore.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.auric.themoviessub1byauric.androidcore.Constant.IMAGE_BASE_URL
import com.auric.themoviessub1byauric.androidcore.Constant.ITEMS_POSTER_RADIUS
import com.auric.themoviessub1byauric.androidcore.Constant.RADIUS
import com.auric.themoviessub1byauric.androidcore.Constant.SAMPLING
import com.auric.themoviessub1byauric.androidcore.R
import com.auric.themoviessub1byauric.androidcore.databinding.ListItemRowMoviesBinding
import com.auric.themoviessub1byauric.androidcore.domain.model.TheMovies
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.placeholderOf
import jp.wasabeef.glide.transformations.BlurTransformation
import android.view.LayoutInflater.from as layoutInflater
import com.auric.themoviessub1byauric.androidcore.databinding.ListItemRowMoviesBinding.inflate as ListItemRowMoviesBinding
class TheMoviesAdapter: RecyclerView.Adapter<TheMoviesAdapter.TheMoviesViewHolder>() {

    private val moviesList = ArrayList<TheMovies>()
    var onItemClick: ((TheMovies) -> Unit)? = null
    private var oldMoviesList = emptyList<TheMovies>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TheMoviesViewHolder {
        viewGroup.apply {
            ListItemRowMoviesBinding(layoutInflater(context), this, false).also {
                return TheMoviesViewHolder(it)
            }
        }
    }

    override fun onBindViewHolder(holder: TheMoviesViewHolder, position: Int) {
        moviesList[position].also {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = moviesList.size

    fun setData(newMoviesList: List<TheMovies>?) {
        if (newMoviesList == null) return
        val diffUtilCallback = MyDiffUtil(moviesList, newMoviesList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        moviesList.clear()
        moviesList.addAll(newMoviesList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class TheMoviesViewHolder(private val itemListMoviesBinding: ListItemRowMoviesBinding) : RecyclerView.ViewHolder(itemListMoviesBinding.root) {
        fun bind(theMovies: TheMovies) {
            itemListMoviesBinding.apply {
                theMovies.apply {
                    itemView.apply {
                        Glide
                            .with(context)
                            .load("$IMAGE_BASE_URL$backdrop")
                            .apply(placeholderOf(R.drawable.background_loading_backdrop).error(R.drawable.background_loading_backdrop))
                            .apply(
                                RequestOptions.bitmapTransform(
                                    BlurTransformation(
                                        RADIUS,
                                        SAMPLING
                                    )
                                )
                            )
                            .into(ivBackdrop)
                        Glide
                            .with(context)
                            .load("$IMAGE_BASE_URL$poster")
                            .transform(RoundedCorners(ITEMS_POSTER_RADIUS))
                            .apply(placeholderOf(R.drawable.background_loading_poster).error(R.drawable.background_loading_poster))
                            .into(ivPoster)
                        tvTitle.text = title
                        tvRating.text = rating.toString()
                        tvReleaseDate.text = releaseDate
                        tvSynopsis.text = synopsis
                    }
                }
            }
        }

        init {
            itemListMoviesBinding.root.setOnClickListener {
                onItemClick?.invoke(moviesList[absoluteAdapterPosition])
            }
        }
    }
}