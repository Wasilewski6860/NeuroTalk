package com.example.neurotalk.presentation.auth.sign_in.viewmodel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.neurotalk.presentation.auth.AuthNavigator
import com.example.neurotalk.presentation.auth.sign_in.feature.SignInDependencies
import com.example.neurotalk.presentation.auth.sign_up.feature.SignUpDependencies
import com.example.neurotalk.presentation.auth.sign_up.viewmodel.SignUpViewModel
import javax.inject.Inject

class SignInViewModelFactory @Inject constructor(
    private val signInUseCase: LoginUseCase,
    private val navigator: AuthNavigator,
    private val context: Context,
//    private val rootView: View,

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(
                dependencies = SignInDependencies(
                    signInUseCase = signInUseCase,
                    navigator = navigator,
                    context = context,
//                    rootView = rootView
                ),
                savedStateHandle = SavedStateHandle()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
