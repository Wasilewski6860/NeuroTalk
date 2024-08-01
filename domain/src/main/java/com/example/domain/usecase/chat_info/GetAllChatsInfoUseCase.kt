package com.example.domain.usecase.chat_info

import com.example.domain.repository.ChatRepository
import com.example.domain.usecase.session.GetSessionUseCase

class GetAllChatsInfoUseCase(
    private val repository: ChatRepository,
    private val getSessionUseCase: GetSessionUseCase
) {
    suspend operator fun invoke() = repository.getAllChats(getSessionUseCase())
}