package com.example.data.repository

import com.example.data.storage.network.api.UserInfoApi
import com.example.domain.coroutines.Response
import com.example.domain.exceptions.NoUserDataException
import com.example.domain.models.UserInfo
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val api: UserInfoApi) : UserRepository {
    override suspend fun getUserInfo(userId: String?): Response<UserInfo> {
        return try {
            if (userId != null) {
                val data = api.getUserInfo(userId)
                Response.Success(
//                    withContext(Dispatchers.IO) {
//
//                    }
                    UserInfo(
                        userId,
                        data.userName,
                        data.userAvatar
                    )
                )
            } else {
                Response.Failure(NoUserDataException())
            }
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}