package com.example.neurotalk.di.component

import android.app.Activity
import com.example.neurotalk.di.annotation.ActivityScope
import com.example.neurotalk.di.module.AuthActivityModule
import com.example.neurotalk.presentation.auth.AuthActivity
import com.example.neurotalk.presentation.auth.AuthNavigator
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [AuthActivityModule::class])
interface AuthActivityComponent {

    fun inject(activity: AuthActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): AuthActivityComponent
    }
}