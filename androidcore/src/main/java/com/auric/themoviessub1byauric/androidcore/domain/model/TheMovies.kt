package com.auric.themoviessub1byauric.androidcore.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TheMovies(
    val id: Int,
    val backdrop: String,
    val poster: String,
    val title: String,
    val releaseDate: String,
    val rating: Double,
    val synopsis: String,
    val watchlist: Boolean
) : Parcelable