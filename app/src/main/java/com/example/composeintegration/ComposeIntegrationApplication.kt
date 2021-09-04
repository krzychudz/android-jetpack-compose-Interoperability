package com.example.composeintegration

import android.app.Application
import com.example.composeintegration.di.ApplicationComponent
import com.example.composeintegration.di.DaggerApplicationComponent

class ComposeIntegrationApplication: Application() {

    var appComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
    }
}