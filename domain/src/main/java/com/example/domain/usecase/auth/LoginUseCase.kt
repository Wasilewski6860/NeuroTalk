package com.example.domain.usecase.auth

import com.example.domain.coroutines.Response
import com.example.domain.repository.AuthRepository
import com.example.domain.usecase.session.SaveSessionUseCase

class LoginUseCase(private val authRepository: AuthRepository, private val saveSessionUseCase: SaveSessionUseCase) {
    suspend operator fun invoke(email: String, password: String): Response<String> {
        val result = authRepository.login(email = email, password = password)
        if(result is Response.Success) saveSessionUseCase(result.value)
        return result
    }
}