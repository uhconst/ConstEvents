package com.constevents

import android.app.Application

class ConstEventsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.AppTheme)
    }
}
