package com.example.domain.usecase.user_info

import com.example.domain.coroutines.Response
import com.example.domain.exceptions.NoUserDataException
import com.example.domain.models.UserInfo
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.session.GetSessionUseCase
import com.example.domain.usecase.session.GetUserInfoSPUseCase
import com.example.domain.usecase.session.SaveUserInfoSPUseCase

class GetUserInfoUseCase(
    private val repository: UserRepository,
    private val getUserInfoSPUseCase: GetUserInfoSPUseCase,
    private val saveUserInfoSPUseCase: SaveUserInfoSPUseCase,
    private val sessionUseCase: GetSessionUseCase
) {
    suspend operator fun invoke(): Response<UserInfo> {
        val data = repository.getUserInfo(sessionUseCase())
        if (data is Response.Success) {
            saveUserInfoSPUseCase(data.value)
            return data
        } else {
            getUserInfoSPUseCase()?.let { return Response.Success(it) }
            return Response.Failure(NoUserDataException())
        }
    }
}