package com.example.neurotalk.custom_ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.neurotalk.R

class CustomCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var imageView: ImageView
    private var textView: TextView
    private var cardView: CardView

    init {
        inflate(context, R.layout.custom_card, this)
        imageView = findViewById(R.id.cardIconImageView)
        textView = findViewById(R.id.cardTextView)
        cardView = findViewById(R.id.mainCardHolder)

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
            val backgroundColor = attrsArray.getResourceId(
                R.styleable.CustomCardView_cardBackground,
                R.color.light_pink
            )
            attrsArray.recycle()

            imageView.setImageDrawable(AppCompatResources.getDrawable(context, image))
            textView.text = context.getString(text)
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, backgroundColor))
        }
    }

}