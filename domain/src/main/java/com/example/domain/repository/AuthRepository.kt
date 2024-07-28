package com.example.domain.repository

import com.example.domain.coroutines.Response


interface AuthRepository {
    suspend fun register(name: String, email: String, password: String): Response<String>
    suspend fun login(email: String, password: String): Response<String>

}