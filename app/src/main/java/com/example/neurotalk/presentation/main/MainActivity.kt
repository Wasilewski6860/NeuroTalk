package com.example.neurotalk.presentation.main

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.neurotalk.R
import com.example.neurotalk.databinding.ActivityAuthBinding
import com.example.neurotalk.databinding.ActivityMainBinding
import com.example.neurotalk.utils.KeyboardUtils
import com.example.neurotalk.utils.setPaddingToInset
import com.example.neurotalk.utils.setupKeyboardHidingListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            binding.mainRootView setPaddingToInset WindowInsetsCompat.Type.navigationBars()
            binding.mainRootView setPaddingToInset WindowInsetsCompat.Type.statusBars()
//            binding.fragmentContainerView setPaddingToInset statusBars()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainRootView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        setupKeyboardHidingListener(binding.mainFragmentContainer, ev)
        return super.dispatchTouchEvent(ev)
    }

}