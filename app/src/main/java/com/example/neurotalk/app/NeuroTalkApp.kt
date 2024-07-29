package com.example.neurotalk.app

import android.app.Application
import com.example.neurotalk.di.ApplicationComponent
import com.example.neurotalk.di.DaggerApplicationComponent

class NeuroTalkApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}