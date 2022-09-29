package com.auric.themoviessub1byauric

import android.app.Application
import com.auric.themoviessub1byauric.androidcore.di.databaseModule
import com.auric.themoviessub1byauric.androidcore.di.networkModule
import com.auric.themoviessub1byauric.androidcore.di.repositoryModule
import com.auric.themoviessub1byauric.di.useCaseModule
import com.auric.themoviessub1byauric.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Themoviessub1byauric : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@Themoviessub1byauric)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}