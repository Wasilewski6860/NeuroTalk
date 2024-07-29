package com.example.neurotalk.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.neurotalk.di.module.AppModule
import com.example.neurotalk.di.module.DataModule
import com.example.neurotalk.di.module.DomainModule
import com.example.neurotalk.di.module.ViewModelModule
import com.example.neurotalk.presentation.auth.sign_in.SignInFragment
import com.example.neurotalk.presentation.auth.sign_in.SignInViewModel
import com.example.neurotalk.presentation.auth.sign_up.SignUpFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
//    AppModule::class,
    DataModule::class,
    DomainModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {

    fun inject(fragment: SignInFragment)

    fun inject(fragment: SignUpFragment)

    fun getMap(): Map<Class<*>, ViewModel>

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

}