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
abstract class ViewModelModule {
    @Binds
    @ClassKey(SignInViewModel::class)
    @IntoMap
    abstract fun signInViewModel(signInViewModel: SignInViewModel): ViewModel

    @Binds
    @ClassKey(SignUpViewModel::class)
    @IntoMap
    abstract fun signUpViewModel(signUpViewModel: SignUpViewModel): ViewModel
}