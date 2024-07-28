package com.example.neurotalk.custom_ui

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.neurotalk.R
import com.example.neurotalk.databinding.CustomTabBarBinding

class RegistrationTabBar(
    context: Context,
    attrs: AttributeSet?
) : RelativeLayout(context, attrs) {

    private var binding: CustomTabBarBinding =
        CustomTabBarBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var listTabName: List<String>
    private lateinit var listTabTextViews: List<TextView>

    private val isDarkThemeEnabled = when (
        resources.configuration.uiMode
                and
                android.content.res.Configuration.UI_MODE_NIGHT_MASK
    ) {
        android.content.res.Configuration.UI_MODE_NIGHT_NO -> false
        else -> true
    }

    private var isAnimating = false

    private var onTabSelectedListener: ((Int) -> Unit)? = null

    init {
        setupAttrs(attrs)
        setupUI()
    }

    fun attachTo(viewPager: ViewPager2) {
        onTabSelectedListener = {
            viewPager.setCurrentItem(it, true)
        }

        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (!isAnimating) {
                    onTabSelected(position)
                }
            }
        })
    }

    private fun setupAttrs(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.RegistrationTabBar, 0, 0
        )

        listTabName = typedArray
            .getTextArray(R.styleable.RegistrationTabBar_android_entries)
            .toList().map {
                it.toString()
            }

        typedArray.recycle()
    }

    private fun setupUI() {
        listTabTextViews = listTabName.mapIndexed { index, tabName ->
            initTabTextView(tabName, index)
        }

        binding.viewTabsWrapper.apply {
            weightSum = listTabTextViews.size.toFloat()
            listTabTextViews.forEach {
                this.addView(it)
            }
        }

        binding.viewIndicatorWrapper.apply {
            weightSum = listTabTextViews.size.toFloat()
        }
    }

    private fun initTabTextView(tabName: String, index: Int) = TextView(context).apply {
        typeface = Typeface.createFromAsset(context.assets, "font/poppins_medium.ttf")
        text = tabName
        layoutParams = LinearLayout.LayoutParams(
            0, ViewGroup.LayoutParams.MATCH_PARENT, 1f
        )
        gravity = Gravity.CENTER
        setTextColor(
            ContextCompat.getColor(
                this.context,
                if (!isDarkThemeEnabled) R.color.black else R.color.white
            )
        )
        textSize = 16f

        setOnClickListener {
            onTabSelected(index)
        }
    }

    private fun onTabSelected(index: Int) {
        ObjectAnimator.ofFloat(
            binding.viewIndicator,
            View.TRANSLATION_X,
            binding.viewIndicator.x,
            listTabTextViews[index].x
        ).apply {
            duration = 300
            onTabSelectedListener?.invoke(index)
            isAnimating = true
            start()
            doOnEnd { isAnimating = false }
        }

        when (index) {
            0 -> {
                listTabTextViews[0].setTextColor(ContextCompat.getColor(context, R.color.white))
                listTabTextViews[1].setTextColor(ContextCompat.getColor(context, R.color.black))
            }

            1 -> {
                listTabTextViews[1].setTextColor(ContextCompat.getColor(context, R.color.white))
                listTabTextViews[0].setTextColor(ContextCompat.getColor(context, R.color.black))
            }
        }
    }

}