package com.example.neurotalk.custom_ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.neurotalk.R

class MainScreenHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var userNameTextView: TextView
    private var userAvatarImageView: ImageView

    init {
        inflate(context, R.layout.custom_header, this)
        userNameTextView = findViewById(R.id.headerUserNameTitleTextView)
        userAvatarImageView = findViewById(R.id.accountAvatarImageView)

        attrs?.let {
            val attrsArray = context.theme.obtainStyledAttributes(
                attrs, R.styleable.MainScreenHeaderView, 0, 0
            )
            val userNameText = attrsArray.getResourceId(
                R.styleable.CustomCardView_cardText,
                R.string.stub
            )
            val userAvatar = attrsArray.getResourceId(
                R.styleable.CustomCardView_cardImage,
                R.drawable.avatar_default
            )
            attrsArray.recycle()

            userNameTextView.text = context.getString(userNameText)
            userNameTextView.setTextColor(context.getColor(R.color.light_pink))
            userAvatarImageView.setImageDrawable(context.getDrawable(userAvatar))
        }
    }

    var userNameTitle: String
        get() = userNameTextView.text.toString()
        set(value) {
            userNameTextView.text = value
        }

}