package com.example.neurotalk.presentation.auth.sign_in.feature

import com.example.domain.coroutines.Response

sealed class SignInMessage {

    data class SignInButtonClicked(val email: String, val password: String) : SignInMessage()

    data class SignInResponse(val response: Response<String>) : SignInMessage()

}