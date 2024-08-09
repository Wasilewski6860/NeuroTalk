package com.example.neurotalk.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.neurotalk.R

class NavigationManager(private val fragmentManager: FragmentManager) {

    fun navigateTo(
        fragment: Fragment,
        container: Int = R.id.mainFragmentContainer,
        backStack: String,
        animation: String,
        args: Bundle? = null
    ) {
        val animParamsList: List<Int> = when (animation) {
            NavigationAnimations.APPEAR_FROM_LEFT -> listOf(
                R.anim.anim_left_to_center, R.anim.anim_center_to_right,
                R.anim.anim_right_to_center, R.anim.anim_center_to_left
            )
            NavigationAnimations.APPEAR_FROM_RIGHT -> listOf(
                R.anim.anim_right_to_center, R.anim.anim_center_to_left,
                R.anim.anim_left_to_center, R.anim.anim_center_to_right
            )
            else -> emptyList()
        }

        fragmentManager.beginTransaction()
            .setCustomAnimations(
                animParamsList[0],
                animParamsList[1],
                animParamsList[2],
                animParamsList[3]
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

    fun navigateTo(
        fragment: Fragment,
        container: Int,
        backStack: String,
        args: Bundle? = null
    ) {
        fragmentManager.beginTransaction()
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

object NavigationAnimations {
    const val APPEAR_FROM_RIGHT = "afr"
    const val APPEAR_FROM_LEFT = "afl"
}