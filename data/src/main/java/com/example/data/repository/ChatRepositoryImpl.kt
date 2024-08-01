package com.example.data.repository

import com.example.data.storage.network.api.ChatApi
import com.example.domain.coroutines.Response
import com.example.domain.exceptions.NoUserDataException
import com.example.domain.models.ChatInfo as DomainChatInfo
import com.example.domain.repository.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChatRepositoryImpl(private val api: ChatApi) : ChatRepository {

    override suspend fun getAllChats(userId: String?): Response<List<DomainChatInfo>> {
        return try {
            if (userId != null) {
                val data = api.getChatInfo(userId)
                Response.Success(
//                    withContext(Dispatchers.IO) {
//
//                    }
                    data.map { chatInfo ->
                        DomainChatInfo(
                            chatInfo.chatId, chatInfo.chatName, chatInfo.chatAvatar
                        )
                    }
                )
            } else {
                Response.Failure(NoUserDataException())
            }
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun getLastChats(): Response<List<com.example.domain.models.ChatInfo>> {
        TODO("Not yet implemented")
    }

}