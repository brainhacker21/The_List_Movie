package com.auric.themoviessub1byauric.androidcore.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.auric.themoviessub1byauric.androidcore.Constant.DATABASE
import com.auric.themoviessub1byauric.androidcore.source.local.entity.TheMoviesEntity

@Database(entities = [TheMoviesEntity::class], version = 1, exportSchema = false)
abstract class TheMoviesDatabase : RoomDatabase() {

    abstract fun theMoviesDao(): TheMoviesDao

    companion object {
        @Volatile
        private var theMoviesDatabase: TheMoviesDatabase? = null

        fun getDatabase(context: Context): TheMoviesDatabase {
            return theMoviesDatabase ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    TheMoviesDatabase::class.java,
                    DATABASE
                ).build()

                theMoviesDatabase = database
                database
            }
        }
    }

}