package com.iliadavidovich.moviehub

import android.app.Application
import android.os.Bundle
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ApplicationMovieHub: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules()
        }
    }
}