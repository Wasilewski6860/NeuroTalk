package com.example.neurotalk.presentation.auth.sign_up

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {


}