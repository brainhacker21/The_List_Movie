package com.auric.themoviessub1byauric.androidcore.di

import androidx.room.Room
import com.auric.themoviessub1byauric.androidcore.Constant.BASE_URL
import com.auric.themoviessub1byauric.androidcore.Constant.DATABASE
import com.auric.themoviessub1byauric.androidcore.Constant.TIME_OUT
import com.auric.themoviessub1byauric.androidcore.domain.repository.ITheMoviesRepository
import com.auric.themoviessub1byauric.androidcore.source.TheMoviesRepository
import com.auric.themoviessub1byauric.androidcore.source.local.LocalDataSource
import com.auric.themoviessub1byauric.androidcore.source.local.room.TheMoviesDatabase
import com.auric.themoviessub1byauric.androidcore.source.remote.RemoteDataSource
import com.auric.themoviessub1byauric.androidcore.source.remote.network.TMDBService
import com.auric.themoviessub1byauric.androidcore.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<TheMoviesDatabase>().theMoviesDao()
    }
    single {
        Room.databaseBuilder(androidContext(), TheMoviesDatabase::class.java, DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(TMDBService::class.java)
    }
}

val repositoryModule = module {
    single {
        LocalDataSource(get())
    }
    single {
        RemoteDataSource(get())
    }
    factory {
        AppExecutors()
    }
    single<ITheMoviesRepository> {
        TheMoviesRepository(
            get(),
            get(),
            get()
        )
    }
}