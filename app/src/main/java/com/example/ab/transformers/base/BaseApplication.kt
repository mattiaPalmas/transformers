package com.example.ab.transformers.base

import android.app.Application
import com.example.ab.transformers.di.networkModule
import com.example.ab.transformers.di.repoModule
import com.example.ab.transformers.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication {
    class BaseApplication : Application() {

        override fun onCreate() {
            super.onCreate()

            startKoin {
                androidContext(this@BaseApplication)
                modules(listOf(viewModelModule, networkModule, repoModule))
            }
        }
    }
}