package com.step.lclib.work

import android.app.Application


lateinit var appContext: Application
    private set

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this

    }
}