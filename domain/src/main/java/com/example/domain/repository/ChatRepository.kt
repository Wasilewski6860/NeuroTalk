package com.example.domain.repository

import com.example.domain.coroutines.Response
import com.example.domain.models.Chat

interface ChatRepository {
    suspend fun getAllChats(): Response<List<Chat>>
}