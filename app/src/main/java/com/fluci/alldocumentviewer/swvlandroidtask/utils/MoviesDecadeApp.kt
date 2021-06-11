package com.fluci.alldocumentviewer.swvlandroidtask.utils

import android.app.Application
import com.fluci.alldocumentviewer.swvlandroidtask.modules.appmodules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesDecadeApp:Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MoviesDecadeApp)
            modules(appmodules)
        }

    }

    companion object {
        lateinit var instance: MoviesDecadeApp
    }

}