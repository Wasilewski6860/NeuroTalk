package com.example.domain.repository

import com.example.domain.coroutines.Response
import com.example.domain.models.ChatInfo

interface ChatRepository {
    suspend fun getAllChats(userId: String?): Response<List<ChatInfo>>

    suspend fun getLastChats(): Response<List<ChatInfo>>
}