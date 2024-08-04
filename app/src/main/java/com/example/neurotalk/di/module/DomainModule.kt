package com.example.neurotalk.di.module

import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ChatRepository
import com.example.domain.repository.SessionRepository
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.domain.usecase.chat.ConnectChatUseCase
import com.example.domain.usecase.chat.GetMessagesUseCase
import com.example.domain.usecase.chat.SendMessageUseCase
import com.example.domain.usecase.chat_info.GetAllChatsInfoUseCase
import com.example.domain.usecase.session.GetSessionUseCase
import com.example.domain.usecase.session.GetUserInfoSPUseCase
import com.example.domain.usecase.session.IsLoggedUseCase
import com.example.domain.usecase.session.SaveSessionUseCase
import com.example.domain.usecase.session.SaveUserInfoSPUseCase
import com.example.domain.usecase.user_info.GetUserInfoUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository, saveSessionUseCase: SaveSessionUseCase): LoginUseCase {
        return LoginUseCase( authRepository = authRepository, saveSessionUseCase = saveSessionUseCase )
    }

    @Provides
    fun provideRegisterUseCase(authRepository: AuthRepository, saveSessionUseCase: SaveSessionUseCase): RegisterUseCase {
        return RegisterUseCase( authRepository = authRepository, saveSessionUseCase = saveSessionUseCase )
    }

    @Provides
    fun provideGetSessionUseCase( sessionRepository: SessionRepository ): GetSessionUseCase {
        return GetSessionUseCase( sessionRepository = sessionRepository )
    }

    @Provides
    fun provideIsLoggedUseCase( sessionRepository: SessionRepository ): IsLoggedUseCase {
        return IsLoggedUseCase( sessionRepository = sessionRepository )
    }

    @Provides
    fun provideSaveSessionUseCase( sessionRepository: SessionRepository ): SaveSessionUseCase {
        return SaveSessionUseCase( sessionRepository = sessionRepository )
    }

    @Provides
    fun provideGetAllChatsInfoUseCase(
        chatRepository: ChatRepository,
        getSessionUseCase: GetSessionUseCase
    ): GetAllChatsInfoUseCase {
        return GetAllChatsInfoUseCase(
            repository = chatRepository, getSessionUseCase = getSessionUseCase
        )
    }

    @Provides
    fun provideGetUserInfoSPUseCase(repository: SessionRepository): GetUserInfoSPUseCase {
        return GetUserInfoSPUseCase(repository = repository)
    }

    @Provides
    fun provideSaveUserInfoSPUseCase(repository: SessionRepository): SaveUserInfoSPUseCase {
        return SaveUserInfoSPUseCase(repository = repository)
    }

    @Provides
    fun provideGetUserInfoUseCase(
        repository: UserRepository,
        getUserInfoSPUseCase: GetUserInfoSPUseCase,
        saveUserInfoSPUseCase: SaveUserInfoSPUseCase,
        getSessionUseCase: GetSessionUseCase
    ) : GetUserInfoUseCase {
        return GetUserInfoUseCase(
            repository = repository,
            getUserInfoSPUseCase = getUserInfoSPUseCase,
            saveUserInfoSPUseCase = saveUserInfoSPUseCase,
            sessionUseCase = getSessionUseCase
        )
    }

    @Provides
    fun provideSendMessageUseCase(repository: ChatRepository): SendMessageUseCase {
        return SendMessageUseCase(chatRepository = repository)
    }

    @Provides
    fun provideConnectChatUseCase(repository: ChatRepository): ConnectChatUseCase {
        return ConnectChatUseCase(chatRepository = repository)
    }

    @Provides
    fun provideGetMessagesUseCase(repository: ChatRepository): GetMessagesUseCase {
        return GetMessagesUseCase(chatRepository = repository)
    }
}
