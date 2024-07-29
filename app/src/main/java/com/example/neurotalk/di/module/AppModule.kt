package com.example.neurotalk.di.module

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.neurotalk.di.annotation.ActivityScope
import com.example.neurotalk.presentation.auth.AuthNavigator
import com.example.neurotalk.presentation.auth.sign_in.SignInViewModel
import com.example.neurotalk.presentation.auth.sign_up.SignUpDependencies
import com.example.neurotalk.presentation.auth.sign_up.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideAuthNavigator(application: Application): AuthNavigator {
        return AuthNavigator(application)
    }

    @Singleton
    @Provides
    fun provideSignUpDependencies(
        signUpUseCase: RegisterUseCase,
        navigator: AuthNavigator,
        context: Context,
    ): SignUpDependencies {
        return SignUpDependencies(
            signUpUseCase = signUpUseCase,
            navigator = navigator,
            context = context
        )
    }
    @Singleton
    @Provides
    fun provideSavedStateHandle(): SavedStateHandle {
        return SavedStateHandle()
    }
}