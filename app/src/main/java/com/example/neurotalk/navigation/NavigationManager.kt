package com.example.neurotalk.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.neurotalk.R

class NavigationManager(private val fragmentManager: FragmentManager) {

    fun navigateTo(
        fragment: Fragment,
        container: Int,
        backStack: String,
        enterAnim: Int,
        exitAnim: Int,
        popEnterAnim: Int,
        popExitAnim: Int,
        args: Bundle? = null
    ) {
        fragmentManager.beginTransaction()
            .setCustomAnimations(
                enterAnim,
                exitAnim,
                popEnterAnim,
                popExitAnim
            )
            .replace(
                container,
                fragment.apply {
                    args?.let {
                        arguments = it
                    }
                }
            )
            .addToBackStack(backStack)
            .commit()
    }

    fun navigateBack() {
        fragmentManager.popBackStack()
    }
}
