package com.example.neurotalk.presentation.main.chat.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.chat.ConnectChatUseCase
import com.example.domain.usecase.chat.GetMessagesUseCase
import com.example.domain.usecase.chat.SendMessageUseCase
import com.example.domain.usecase.chat_info.GetAllChatsInfoUseCase
import com.example.domain.usecase.user_info.GetUserInfoUseCase
import com.example.neurotalk.presentation.main.chat.feature.ChatDependencies
import com.example.neurotalk.presentation.main.home.feature.HomeDependencies
import com.example.neurotalk.presentation.main.home.viewmodel.HomeViewModel
import javax.inject.Inject

class ChatViewModelFactory @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val connectChatUseCase: ConnectChatUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(
                dependencies = ChatDependencies(
                    sendMessageUseCase = sendMessageUseCase,
                    getMessagesUseCase = getMessagesUseCase,
                    connectChatUseCase = connectChatUseCase
                ),
                savedStateHandle = SavedStateHandle()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}