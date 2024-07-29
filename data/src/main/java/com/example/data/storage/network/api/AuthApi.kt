package com.example.data.storage.network.api

import com.example.data.model.request.auth.LoginRequest
import com.example.data.model.request.auth.RegisterRequest
import com.example.data.model.response.auth.AuthResponse
import com.example.data.storage.network.Endpoints.LOGIN
import com.example.data.storage.network.Endpoints.REGISTER
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST(REGISTER)
    suspend fun register(@Body request: RegisterRequest): AuthResponse

    @GET(LOGIN)
    suspend fun login(@Body request: LoginRequest): AuthResponse


}
