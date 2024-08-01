package com.example.data.model.response.chat_info

import com.google.gson.annotations.SerializedName

data class ChatInfo(
    @SerializedName("chatId") val chatId: String,
    @SerializedName("chatName") val chatName: String,
    @SerializedName("chatAvatar") val chatAvatar: String,
)
