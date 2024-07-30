package com.example.neurotalk.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.neurotalk.presentation.auth.AuthNavigator
import com.example.neurotalk.presentation.auth.sign_in.feature.SignInDependencies
import com.example.neurotalk.presentation.auth.sign_up.feature.SignUpDependencies
import dagger.Module
import dagger.Provides
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
    fun provideSignInDependencies(
        signInUseCase: LoginUseCase,
        navigator: AuthNavigator,
        context: Context,
    ): SignInDependencies {
        return SignInDependencies(
            signInUseCase = signInUseCase,
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