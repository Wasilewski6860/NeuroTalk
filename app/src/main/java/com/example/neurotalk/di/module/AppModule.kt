package com.example.neurotalk.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.neurotalk.presentation.auth.sign_in.SignInViewModel
import com.example.neurotalk.presentation.auth.sign_up.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }
}