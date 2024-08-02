package com.example.domain.usecase.session

import com.example.domain.repository.SessionRepository

class GetUserInfoSPUseCase(private val repository: SessionRepository) {
    operator fun invoke() = repository.getUserInfo()
}