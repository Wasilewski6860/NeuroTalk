package com.example.neurotalk.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.neurotalk.di.component.AuthActivityComponent
import com.example.neurotalk.di.module.AppModule
import com.example.neurotalk.di.module.DataModule
import com.example.neurotalk.di.module.DomainModule
import com.example.neurotalk.di.module.ViewModelModule
import com.example.neurotalk.presentation.auth.AuthActivity
import com.example.neurotalk.presentation.auth.sign_in.SignInFragment
import com.example.neurotalk.presentation.auth.sign_in.viewmodel.SignInViewModelFactory
import com.example.neurotalk.presentation.auth.sign_up.SignUpFragment
import com.example.neurotalk.presentation.auth.sign_up.viewmodel.SignUpViewModelFactory
import com.example.neurotalk.presentation.main.home.HomeFragment
import com.example.neurotalk.presentation.main.home.viewmodel.HomeViewModel
import com.example.neurotalk.presentation.main.home.viewmodel.HomeViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    DataModule::class,
    DomainModule::class,
    ViewModelModule::class
],
)
interface ApplicationComponent {

    fun inject(activity: AuthActivity)
    fun inject(fragment: SignInFragment)

    fun inject(fragment: SignUpFragment)

    fun inject(fragment: HomeFragment)

    fun getMap(): Map<Class<*>, ViewModel>

    fun signUpVmFactory(): SignUpViewModelFactory
    fun signInVmFactory(): SignInViewModelFactory
    fun authActivityComponent(): AuthActivityComponent.Factory
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

    fun homeVmFactory(): HomeViewModelFactory

}