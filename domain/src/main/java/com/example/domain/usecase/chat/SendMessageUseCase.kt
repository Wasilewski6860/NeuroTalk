package com.example.domain.usecase.chat

import com.example.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class SendMessageUseCase(private val chatRepository: ChatRepository) {
    operator fun invoke(message: String) = chatRepository.sendMessage(message)
}