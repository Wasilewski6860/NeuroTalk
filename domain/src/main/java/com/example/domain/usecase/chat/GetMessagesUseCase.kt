package com.example.domain.usecase.chat

import com.example.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class GetMessagesUseCase(private val chatRepository: ChatRepository) {
    operator fun invoke(): Flow<String> = chatRepository.getMessage()

}