package com.example.neurotalk.di.module

import android.app.Application
import android.content.Context
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.PreferencesRepositoryImpl
import com.example.data.repository.SessionRepositoryImpl
import com.example.data.storage.network.Endpoints.BASE_URL
import com.example.data.storage.network.api.AuthApi
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.PreferencesRepository
import com.example.domain.repository.SessionRepository
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
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
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
    fun provideSessionRepository(preferencesRepository: PreferencesRepository): SessionRepository {
        return SessionRepositoryImpl(
            preferencesRepository = preferencesRepository
        )
    }
}