package com.example.neurotalk.custom_ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.neurotalk.presentation.auth.sign_up.SignUpFragment
import com.example.neurotalk.presentation.auth.sign_in.SignInFragment

class RegistrationViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SignInFragment()
            else -> SignUpFragment()
        }
    }

}