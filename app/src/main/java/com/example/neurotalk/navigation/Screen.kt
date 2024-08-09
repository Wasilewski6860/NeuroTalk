package com.example.neurotalk.navigation

import androidx.fragment.app.Fragment
import com.example.neurotalk.presentation.auth.sign_in.SignInFragment
import com.example.neurotalk.presentation.auth.sign_up.SignUpFragment
import com.example.neurotalk.presentation.main.home.HomeFragment
import kotlin.reflect.KClass

sealed class Screen(val fragmentClass: KClass<out Fragment>) {
    data object SignInScreen : Screen(SignInFragment::class)
    data object SignUpScreen : Screen(SignUpFragment::class)
    data object HomeScreen : Screen(HomeFragment::class)
}