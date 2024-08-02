package com.example.neurotalk.presentation.main.home.feature

import com.example.base.coroutines.Effect
import com.example.base.coroutines.Update
import com.example.base.coroutines.noEffects
import com.example.base.coroutines.with
import com.example.domain.coroutines.Response
import com.example.domain.models.ChatInfo
import com.example.domain.models.UserInfo
import com.example.domain.usecase.chat_info.GetAllChatsInfoUseCase
import com.example.domain.usecase.user_info.GetUserInfoUseCase

object GettingUserAndChatsDataFeature {

    val initialUpdate: Update<HomeState, HomeMessage, HomeDependencies> = HomeState.Loading with setOf(
        HomeEffect.GetUserInfo(),
        HomeEffect.GetChats()
    )

    fun restore(state: HomeState): Update<HomeState, HomeMessage, HomeDependencies> =
        state with setOf(
            HomeEffect.GetUserInfo(),
            HomeEffect.GetChats()
        )

    fun update(message: HomeMessage, state: HomeState): Update<HomeState, HomeMessage, HomeDependencies> {
        return when (message) {
            is HomeMessage.UserInfoResponse -> handleGettingUserInfo(message.response)
            is HomeMessage.ChatsResponse -> handleGettingChats(message.response)
            is HomeMessage.HomeTest -> initialUpdate
        }
    }

    private fun handleGettingUserInfo(
        response: Response<UserInfo>
    ) : Update<HomeState, HomeMessage, HomeDependencies> {
        return when (response) {
            is Response.Success -> HomeState.UserInfoLoaded(response.value) with noEffects<HomeMessage, HomeDependencies>()
            is Response.Failure -> HomeState.UserInfoLoadingError with noEffects()
        }
    }

    private fun handleGettingChats(
        response: Response<List<ChatInfo>>
    ) : Update<HomeState, HomeMessage, HomeDependencies> {
        return when (response) {
            is Response.Success -> HomeState.ChatsLoaded(response.value) with noEffects()
            is Response.Failure -> HomeState.ChatsLoadingError with noEffects()
        }
    }

}

object HomeEffect {

    class GetUserInfo : Effect<HomeDependencies, HomeMessage> by Effect.single({ dependencies ->
        val result = dependencies.getUserInfoUseCase()
        return@single HomeMessage.UserInfoResponse(result)
    })

    class GetChats : Effect<HomeDependencies, HomeMessage> by Effect.single({ dependencies ->
        val result = dependencies.getAllChatsInfoUseCase()
        return@single HomeMessage.ChatsResponse(result)
    })

}

class HomeDependencies(
    val getUserInfoUseCase: GetUserInfoUseCase,
    val getAllChatsInfoUseCase: GetAllChatsInfoUseCase
)