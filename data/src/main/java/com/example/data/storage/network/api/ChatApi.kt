package com.example.data.storage.network.api

import com.example.data.model.response.chat_info.ChatInfo
import com.example.data.storage.network.Endpoints.GET_CHAT
import retrofit2.http.GET
import retrofit2.http.Header

interface ChatApi {

    @GET(GET_CHAT)
    suspend fun getChatInfo(@Header("user_id") userId: String) : List<ChatInfo>

}