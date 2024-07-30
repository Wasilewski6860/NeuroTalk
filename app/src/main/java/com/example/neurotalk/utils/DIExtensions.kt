package com.example.neurotalk.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.neurotalk.app.NeuroTalkApp
import com.example.neurotalk.di.ApplicationComponent
import com.example.neurotalk.presentation.auth.sign_in.viewmodel.SignInViewModelFactory

fun Context.appComponent(): ApplicationComponent {
    return when (this) {
        is NeuroTalkApp -> applicationComponent
        else -> this.applicationContext.appComponent()
    }
}
