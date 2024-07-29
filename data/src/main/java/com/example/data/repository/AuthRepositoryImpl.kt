package com.example.data.repository

import android.util.Log
import com.example.data.model.request.auth.LoginRequest
import com.example.data.model.request.auth.RegisterRequest
import com.example.data.storage.network.api.AuthApi
import com.example.domain.coroutines.Response
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(private val authApi: AuthApi) :
    AuthRepository {

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Response<String> {
        return try {
            Response.Success(
                withContext(Dispatchers.IO) {
                    authApi.register(RegisterRequest(name = name, email = email, password = password)).userId
                }
            )
        } catch (ex: Exception) {
            Response.Failure(ex)
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Response<String> {
        return try {
            Response.Success(
                withContext(Dispatchers.IO) {
                    authApi.login(LoginRequest(email = email, password = password)).userId
                }
            )
        } catch (ex: Exception) {
            Response.Failure(ex)
        }
    }


}
