package com.example.domain.usecase.chat

import com.example.domain.coroutines.Response
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ChatRepository
import com.example.domain.usecase.session.SaveSessionUseCase

class ConnectChatUseCase(private val chatRepository: ChatRepository) {
    operator fun invoke() {
        chatRepository.connect()
    }
}