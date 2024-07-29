package com.example.domain.usecase.session

import com.example.domain.coroutines.Response
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.SessionRepository

class SaveSessionUseCase(private val sessionRepository: SessionRepository) {
    operator fun invoke(session: String) = sessionRepository.saveSession(session)
}