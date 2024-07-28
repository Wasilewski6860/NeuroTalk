package com.example.data.repository

import com.example.domain.repository.PreferencesRepository
import com.example.domain.repository.SessionRepository

class SessionRepositoryImpl(
    private val preferencesRepository: PreferencesRepository
) : SessionRepository {

    private companion object {
        const val AUTH_SESSION = "AUTH_SESSION"
    }

    override fun saveSession(authSession: String) {
        preferencesRepository.putString(
            AUTH_SESSION,
            authSession
        )
    }

    override fun deleteSession() {
        preferencesRepository.remove(AUTH_SESSION)
    }

    override fun getSession(): String? = preferencesRepository.getStringOrNull(AUTH_SESSION)

}