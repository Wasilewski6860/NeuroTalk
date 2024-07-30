package com.example.neurotalk.presentation.auth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.neurotalk.R
import com.example.neurotalk.app.NeuroTalkApp
import com.example.neurotalk.custom_ui.RegistrationViewPagerAdapter
import com.example.neurotalk.databinding.ActivityAuthBinding
import com.example.neurotalk.databinding.ActivityMainBinding
import com.example.neurotalk.di.component.AuthActivityComponent

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val viewPagerAdapter = RegistrationViewPagerAdapter(this)

    private lateinit var activityComponent: AuthActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)

        activityComponent = (application as NeuroTalkApp).applicationComponent
            .authActivityComponent().create(this@AuthActivity)
        activityComponent.inject(this@AuthActivity)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.authRootView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initTabBarAndViewPager()

    }

    private fun initTabBarAndViewPager() = with(binding) {
        registrationViewPager.adapter = viewPagerAdapter
        registrationTabBar.attachTo(registrationViewPager)
    }
}