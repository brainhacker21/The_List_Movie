package com.auric.themoviessub1byauric.androidcore.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.auric.themoviessub1byauric.androidcore.Constant.DATABASE_NAME

@Entity(tableName = DATABASE_NAME)
data class TheMoviesEntity(
    @PrimaryKey
    val id: Int,
    val backdrop: String,
    val poster: String,
    val title: String,
    val releaseDate: String,
    val rating: Double,
    val synopsis: String,
    var watchlist: Boolean = false
)