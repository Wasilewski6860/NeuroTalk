package com.example.neurotalk.presentation.auth

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsCompat.Type.statusBars
import com.example.domain.usecase.session.IsLoggedUseCase
import com.example.neurotalk.R
import com.example.neurotalk.app.NeuroTalkApp
import com.example.neurotalk.custom_ui.RegistrationViewPagerAdapter
import com.example.neurotalk.databinding.ActivityAuthBinding
import com.example.neurotalk.databinding.ActivityMainBinding
import com.example.neurotalk.di.component.AuthActivityComponent
import com.example.neurotalk.presentation.main.MainActivity
import com.example.neurotalk.utils.setPaddingToInset
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val viewPagerAdapter = RegistrationViewPagerAdapter(this)

    @Inject
    lateinit var isLoggedUseCase: IsLoggedUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)

        (application as NeuroTalkApp).applicationComponent
            .inject(this@AuthActivity)

        if(isLoggedUseCase()) {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            // Запуск активности
            application.startActivity(intent)
        }
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            binding.authRootView setPaddingToInset WindowInsetsCompat.Type.navigationBars()
            binding.authRootView setPaddingToInset statusBars()
//            binding.fragmentContainerView setPaddingToInset statusBars()
        }

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