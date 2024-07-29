package com.example.domain.usecase.session

import com.example.domain.repository.SessionRepository

class IsLoggedUseCase(private val sessionRepository: SessionRepository) {
    operator fun invoke() = sessionRepository.getSession() != null
}