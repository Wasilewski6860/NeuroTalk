package com.example.neurotalk.di.module

import android.app.Application
import android.content.Context
import com.example.data.model.response.chat_info.ChatInfo
import com.example.data.model.response.user_info.UserInfo
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.ChatRepositoryImpl
import com.example.data.repository.PreferencesRepositoryImpl
import com.example.data.repository.SessionRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.data.storage.network.Endpoints.BASE_URL
import com.example.data.storage.network.api.AuthApi
import com.example.data.storage.network.api.ChatApi
import com.example.data.storage.network.api.UserInfoApi
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ChatRepository
import com.example.domain.repository.PreferencesRepository
import com.example.domain.repository.SessionRepository
import com.example.domain.repository.UserRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
        return builder.build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun providesUserInfoApi(retrofit: Retrofit): UserInfoApi {
        return object : UserInfoApi {
            override suspend fun getUserInfo(userId: String): UserInfo {
                return UserInfo(
                    userName = "Vasiya",
                    userEmail = "Vasiya@krut.com",
                    userAvatar = "https://s0.rbk.ru/v6_top_pics/media/img/7/19/346964088984197.jpeg"
                )
            }
        }
    }

    @Provides
    @Singleton
    fun providesChatApi(retrofit: Retrofit): ChatApi {
        return object : ChatApi {
            override suspend fun getChatInfo(userId: String): List<ChatInfo> {
                return listOf(
                    ChatInfo(
                        chatId = "1",
                        chatName = "some chat",
                        chatAvatar = "https://s0.rbk.ru/v6_top_pics/media/img/7/19/346964088984197.jpeg"
                    )
                )
            }
        }
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi): AuthRepository {
        return AuthRepositoryImpl(
            authApi = authApi
        )
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(context: Context): PreferencesRepository {
        return PreferencesRepositoryImpl(
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideSessionRepository(
        preferencesRepository: PreferencesRepository,
        gson: Gson
    ): SessionRepository {
        return SessionRepositoryImpl(
            preferencesRepository = preferencesRepository,
            gson = gson
        )
    }

    @Provides
    @Singleton
    fun provideChatRepository(
        api: ChatApi
    ): ChatRepository {
        return ChatRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        api: UserInfoApi
    ): UserRepository {
        return UserRepositoryImpl(api = api)
    }

}