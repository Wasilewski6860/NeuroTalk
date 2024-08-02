package com.example.neurotalk.presentation.main.home.feature

import com.example.domain.coroutines.Response
import com.example.domain.models.ChatInfo
import com.example.domain.models.UserInfo

sealed class HomeMessage {

    data class UserInfoResponse(val response: Response<UserInfo>) : HomeMessage()

    data class ChatsResponse(val response: Response<List<ChatInfo>>) : HomeMessage()

    data object HomeTest : HomeMessage()

}