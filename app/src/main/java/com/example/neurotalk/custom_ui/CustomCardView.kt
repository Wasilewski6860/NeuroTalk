package com.example.neurotalk.custom_ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.neurotalk.R

class CustomCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var imageView: ImageView
    private var textView: TextView

    init {
        inflate(context, R.layout.custom_card, this)
        imageView = findViewById(R.id.cardIconImageView)
        textView = findViewById(R.id.cardTextView)

        attrs?.let {
            val attrsArray = context.theme.obtainStyledAttributes(
                attrs, R.styleable.CustomCardView, 0, 0
            )
            val image = attrsArray.getResourceId(
                R.styleable.CustomCardView_cardImage,
                R.drawable.chat_icon
            )
            val text = attrsArray.getResourceId(
                R.styleable.CustomCardView_cardText,
                R.string.stub
            )
            attrsArray.recycle()

            imageView.setImageDrawable(context.getDrawable(image))
            textView.text = context.getString(text)
        }
    }

    val cardTitle get() = textView.text

}