package com.example.data.model.response.user_info

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("userName") val userName: String,
    @SerializedName("userEmail") val userEmail: String,
    @SerializedName("userAvatar") val userAvatar: String
)
