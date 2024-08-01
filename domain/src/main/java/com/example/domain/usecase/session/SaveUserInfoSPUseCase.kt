package com.example.domain.usecase.session

import com.example.domain.models.UserInfo
import com.example.domain.repository.SessionRepository

class SaveUserInfoSPUseCase(private val repository: SessionRepository) {
    operator fun invoke(userInfo: UserInfo) = repository.saveUserInfo(userInfo)
}