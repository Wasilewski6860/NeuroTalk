package com.example.data.model.response.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("userId") val userId: String
)