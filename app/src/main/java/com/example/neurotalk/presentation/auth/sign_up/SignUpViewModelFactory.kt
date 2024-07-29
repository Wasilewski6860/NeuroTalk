package com.example.neurotalk.presentation.auth.sign_up

import android.content.Context
import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.neurotalk.navigation.Navigator
import com.example.neurotalk.presentation.auth.AuthNavigator
import javax.inject.Inject

class SignUpViewModelFactory @Inject constructor(
    private val signUpUseCase: RegisterUseCase,
    private val navigator: AuthNavigator,
    private val context: Context,
//    private val rootView: View,

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(
                dependencies = SignUpDependencies(
                   signUpUseCase = signUpUseCase,
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
