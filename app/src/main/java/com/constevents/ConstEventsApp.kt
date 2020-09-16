package com.constevents

import android.app.Application
import com.constevents.di.Modules.featureModules
import com.constevents.di.Modules.rxSchedulersModule
import com.constevents.di.Modules.servicesModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ConstEventsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ConstEventsApp)
            modules(
                servicesModules
                    .plus(rxSchedulersModule)
                    .plus(featureModules)
            )
        }
    }
}
