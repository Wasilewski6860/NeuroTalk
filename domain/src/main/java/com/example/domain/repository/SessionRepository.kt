package com.example.domain.repository

interface SessionRepository {
    fun saveSession(authSession: String)
    fun deleteSession()
    fun getSession(): String?
}