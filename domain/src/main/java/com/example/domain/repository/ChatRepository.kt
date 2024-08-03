package com.example.domain.repository

import com.example.domain.coroutines.Response
import com.example.domain.models.ChatInfo
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun connect()
    fun sendMessage(message: String)
    fun getMessage(): Flow<String>
    suspend fun getAllChats(userId: String?): Response<List<ChatInfo>>

    suspend fun getLastChats(): Response<List<ChatInfo>>
}