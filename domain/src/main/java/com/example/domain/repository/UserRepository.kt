package com.example.domain.repository

import com.example.domain.coroutines.Response
import com.example.domain.models.UserInfo

interface UserRepository {
    suspend fun getUserInfo(userId: String?): Response<UserInfo>
}