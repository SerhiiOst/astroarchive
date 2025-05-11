package org.socoding.astroarchive

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.socoding.astroarchive.di.initKoin

class AstroArchiveApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@AstroArchiveApplication)
        }
    }
}