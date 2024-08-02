package com.example.domain.repository

import com.example.domain.models.UserInfo

interface SessionRepository {
    fun saveSession(authSession: String)
    fun deleteSession()
    fun getSession(): String?
    fun saveUserInfo(userInfo: UserInfo)
    fun getUserInfo(): UserInfo?
}