package com.example.data.repository

import com.example.domain.models.UserInfo
import com.example.domain.repository.PreferencesRepository
import com.example.domain.repository.SessionRepository
import com.google.gson.Gson

class SessionRepositoryImpl(
    private val preferencesRepository: PreferencesRepository,
    gson: Gson
) : SessionRepository {

    private val serializationUserInfoAdapter = gson.getAdapter(UserInfo::class.java)

    private companion object {
        const val AUTH_SESSION = "AUTH_SESSION"
        const val USER_KEY = "user_key"
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

    override fun saveUserInfo(userInfo: UserInfo) {
        preferencesRepository.putString(
            USER_KEY,
            serializationUserInfoAdapter.toJson(userInfo)
        )
    }

    override fun getUserInfo(): UserInfo? {
        return serializationUserInfoAdapter.fromJson(
            preferencesRepository.getStringOrNull(USER_KEY)
        )
    }

}