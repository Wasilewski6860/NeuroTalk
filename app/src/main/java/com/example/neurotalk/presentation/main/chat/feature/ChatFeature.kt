package com.example.neurotalk.presentation.main.chat.feature

import android.content.Context
import com.example.base.coroutines.Effect
import com.example.base.coroutines.Update
import com.example.base.coroutines.noEffects
import com.example.base.coroutines.with
import com.example.domain.coroutines.Response
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.domain.usecase.chat.ConnectChatUseCase
import com.example.domain.usecase.chat.GetMessagesUseCase
import com.example.domain.usecase.chat.SendMessageUseCase
import com.example.neurotalk.common.State
import com.example.neurotalk.common.State.Loading.isError
import com.example.neurotalk.common.State.Loading.isLoading
import com.example.neurotalk.common.State.Loading.isSuccess
import com.example.neurotalk.presentation.auth.AuthNavigator
import com.example.neurotalk.presentation.main.home.feature.HomeDependencies
import com.example.neurotalk.presentation.main.home.feature.HomeMessage
import com.example.neurotalk.presentation.main.home.feature.HomeState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

object ChatFeature {

    val initialUpdate: Update<ChatState, ChatMessage, ChatDependencies> = ChatState(
        messages = State.Loading,
        chatInfo = State.Loading
    ) with setOf(ChatEffects.ConnectChat())

    fun restore(state: ChatState): Update<ChatState, ChatMessage, ChatDependencies> =
        state with setOf(ChatEffects.ConnectChat())

    fun update(message: ChatMessage, state: ChatState): Update<ChatState, ChatMessage, ChatDependencies> =
        when (message) {
            is ChatMessage.MessageReceived -> handleMessageReceived(message.message, state)
            is ChatMessage.SendMessage -> handleSendMessage(message.message, state)
            is ChatMessage.MessagesResponse -> handleMessagesResponse(message.messages, state)
            ChatMessage.ChatConnected -> handleConnectionSet(state)
        }

    private fun handleMessageReceived(message: Response<String>, state: ChatState): Update<ChatState, ChatMessage, ChatDependencies> =
        when(message) {
            is Response.Failure -> state.copy(messages = State.Error(message.error)) with noEffects()
            is Response.Success -> state.copy(
                messages = if(state.messages.isLoading()) {
                    State.Success(listOf(message.value))
                } else if(state.messages is State.Success) {
                    State.Success(state.messages.value + message.value)
                } else State.Success(listOf(message.value))
            ) with noEffects()
        }


    private fun handleSendMessage(message: String, state: ChatState): Update<ChatState, ChatMessage, ChatDependencies> =
        state with setOf(
            ChatEffects.SendMessage(message)
        )

    private fun handleMessagesResponse(
        messages: Response<List<String>>,
        state: ChatState
    ): Update<ChatState, ChatMessage, ChatDependencies> =
        when (messages) {
            is Response.Failure -> state.copy(messages = State.Error(messages.error)) with noEffects()
            is Response.Success -> state.copy(
                messages = State.Success(messages.value)
            ) with noEffects()
        }

    private fun handleConnectionSet(state: ChatState): Update<ChatState, ChatMessage, ChatDependencies> =
        state with setOf(ChatEffects.ObserveMessages())

}

object ChatEffects {

    class ConnectChat : Effect<ChatDependencies, ChatMessage> by Effect.single({ dependencies ->
        dependencies.connectChatUseCase()
        return@single ChatMessage.ChatConnected
    })

    data class SendMessage(val message: String) : Effect<ChatDependencies, ChatMessage> by Effect.idle({ dependencies ->
        dependencies.sendMessageUseCase(message)
    })

    class ObserveMessages : Effect<ChatDependencies, ChatMessage> by Effect.flow({ dependencies ->
        dependencies.getMessagesUseCase()
            .map<String, ChatMessage> { message ->
                ChatMessage.MessageReceived(Response.Success(message))
            }
            .catch { throwable ->
                emit(ChatMessage.MessageReceived(Response.Failure(throwable)))
            }
    })
}

class ChatDependencies(
    val sendMessageUseCase: SendMessageUseCase,
    val getMessagesUseCase: GetMessagesUseCase,
    val connectChatUseCase: ConnectChatUseCase
)