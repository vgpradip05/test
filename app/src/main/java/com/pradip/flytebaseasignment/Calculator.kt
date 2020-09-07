package com.pradip.flytebaseasignment

import android.app.Application
import com.pradip.flytebaseasignment.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Calculator : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Calculator)
            modules(listOf(appModule))
        }
    }
}