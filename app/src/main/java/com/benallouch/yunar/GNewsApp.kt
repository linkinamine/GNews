package com.benallouch.yunar

import android.app.Application
import com.benallouch.yunar.di.appModule
import com.benallouch.yunar.di.dataModule
import com.benallouch.yunar.di.scopesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GNewsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GNewsApp)
            modules(listOf(appModule, dataModule, scopesModule))
        }
    }
}