package com.example.neurotalk.presentation.auth.sign_up

import com.example.domain.coroutines.Response
import java.time.LocalTime

sealed class SignUpMessage {

    data class SignUpButtonClicked(val name: String, val email: String, val password: String, val confirmPassword: String) : SignUpMessage()

    data class SignUpResponse(val response: Response<String>) : SignUpMessage()

    // subscriptions
    // here we could have messages from subscriptions
}