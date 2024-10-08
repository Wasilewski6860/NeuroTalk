package com.example.neurotalk.di.module

import androidx.lifecycle.ViewModel
import com.example.neurotalk.presentation.auth.sign_in.viewmodel.SignInViewModel
import com.example.neurotalk.presentation.auth.sign_up.viewmodel.SignUpViewModel
import com.example.neurotalk.presentation.main.chat.viewmodel.ChatViewModel
import com.example.neurotalk.presentation.main.home.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

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

    @Binds
    @ClassKey(HomeViewModel::class)
    @IntoMap
    abstract fun homeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @ClassKey(ChatViewModel::class)
    @IntoMap
    abstract fun chatViewModel(chatViewModel: ChatViewModel): ViewModel
}