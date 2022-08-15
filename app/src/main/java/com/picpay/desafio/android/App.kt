package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.DataModules
import com.picpay.desafio.android.di.DomainModules
import com.picpay.desafio.android.di.UiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }
        DataModules.load()
        DomainModules.load()
    }
}