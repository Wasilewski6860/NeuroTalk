package com.example.neurotalk.presentation.auth

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.neurotalk.navigation.Navigator
import com.example.neurotalk.navigation.Screen
import com.example.neurotalk.presentation.main.MainActivity
import javax.inject.Inject

class AuthNavigator(val application: Application): Navigator {

    override fun forward(screen: Screen) {
        try {
            val intent = Intent(application, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            // Запуск активности
            application.startActivity(intent)
        }
        catch (e: Exception) {
            Log.d("AuthNavigator exception", e.toString())
        }
    }

    override fun back() {

    }

}