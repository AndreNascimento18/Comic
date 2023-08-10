package com.example.comics

import android.app.Application
import com.example.comics.di.appModules
import com.example.comics.timber.LogDebugImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupTimber()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }

    private fun setupTimber() {
        Timber.plant(LogDebugImpl())
    }
}