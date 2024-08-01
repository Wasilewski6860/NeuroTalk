package com.example.neurotalk.presentation.main.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.chat_info.GetAllChatsInfoUseCase
import com.example.domain.usecase.user_info.GetUserInfoUseCase
import com.example.neurotalk.presentation.main.home.feature.HomeDependencies
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getAllChatsInfoUseCase: GetAllChatsInfoUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                dependencies = HomeDependencies(
                    getUserInfoUseCase = getUserInfoUseCase,
                    getAllChatsInfoUseCase = getAllChatsInfoUseCase
                ),
                savedStateHandle = SavedStateHandle()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}