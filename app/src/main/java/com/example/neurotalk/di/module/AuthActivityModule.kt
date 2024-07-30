package com.example.neurotalk.di.module

import android.app.Activity
import android.app.Application
import com.example.neurotalk.presentation.auth.AuthNavigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthActivityModule {

    @Singleton
    @Provides
    fun provideAuthNavigator(application: Application): AuthNavigator {
        return AuthNavigator(application)
    }
}