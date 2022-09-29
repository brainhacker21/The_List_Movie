package com.auric.themoviessub1byauric.androidcore.adapter

import androidx.recyclerview.widget.DiffUtil
import com.auric.themoviessub1byauric.androidcore.domain.model.TheMovies

open class MyDiffUtil(
    private val oldMoviesList: List<TheMovies>,
    private val newMoviesList: List<TheMovies>,
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldMoviesList.size
    }

    override fun getNewListSize(): Int {
        return newMoviesList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMoviesList[oldItemPosition].releaseDate == newMoviesList[newItemPosition].releaseDate
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMoviesList[oldItemPosition].releaseDate == newMoviesList[newItemPosition].releaseDate
    }
}
