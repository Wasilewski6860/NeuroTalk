package com.example.neurotalk.di.module

import com.example.domain.repository.AuthRepository
import com.example.domain.repository.SessionRepository
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.domain.usecase.session.GetSessionUseCase
import com.example.domain.usecase.session.IsLoggedUseCase
import com.example.domain.usecase.session.SaveSessionUseCase
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
}
