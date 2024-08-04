package com.example.neurotalk.presentation.main.chat.feature

import com.example.domain.coroutines.Response

sealed class ChatMessage {
    data class MessageReceived(val message: Response<String>) : ChatMessage()
    data class SendMessage(val message: String) : ChatMessage()
    data class MessagesResponse(val messages: Response<List<String>>) : ChatMessage()
    object ChatConnected : ChatMessage()
}