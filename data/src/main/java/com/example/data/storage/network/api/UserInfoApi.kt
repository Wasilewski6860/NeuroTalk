package com.example.data.storage.network.api

import com.example.data.model.response.user_info.UserInfo
import com.example.data.storage.network.Endpoints.GET_USER
import retrofit2.http.GET
import retrofit2.http.Header

interface UserInfoApi {

    @GET(GET_USER)
    suspend fun getUserInfo(@Header("user_id") userId: String) : UserInfo

}