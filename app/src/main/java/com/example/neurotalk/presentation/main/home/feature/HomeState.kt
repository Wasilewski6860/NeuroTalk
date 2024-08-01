package com.example.neurotalk.presentation.main.home.feature

import com.example.domain.models.ChatInfo
import com.example.domain.models.UserInfo

sealed class HomeState {

    data object Loading : HomeState()

    data class UserInfoLoaded(
        val userInfo: UserInfo
    ) : HomeState()

    data class ChatsLoaded(
        val chatsList: List<ChatInfo>
    ) : HomeState()

    data object UserInfoLoadingError : HomeState()

    data object ChatsLoadingError : HomeState()

    data object Error : HomeState()

}