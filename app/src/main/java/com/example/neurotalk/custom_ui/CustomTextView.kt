package com.example.neurotalk.custom_ui

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.example.neurotalk.R

class CustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private var firstTextView: TextView
    private var secondTextView: TextView

    init {
        inflate(context, R.layout.custom_text_view, this)
        firstTextView = findViewById(R.id.firstWordTextView)
        secondTextView = findViewById(R.id.secondWordTextView)

        attrs?.let {
            val attrsArray = context.theme.obtainStyledAttributes(
                attrs, R.styleable.CustomTextView, 0, 0
            )
            val firstWord = attrsArray.getResourceId(
                R.styleable.CustomTextView_firstWord,
                R.string.hi
            )
            val secondWord = attrsArray.getResourceId(
                R.styleable.CustomTextView_secondWord,
                R.string.stub
            )
            attrsArray.recycle()

            firstTextView.text = context.getString(firstWord)
            secondTextView.text = context.getString(secondWord)
            secondTextView.setTextColor(context.getColor(R.color.medium_pink))
        }
    }

    var firstWord: String
        get() = firstTextView.text.toString()
        set(value) {
            firstTextView.text = value
        }

    var secondWord: String
        get() = secondTextView.text.toString()
        set(value) {
            secondTextView.text = value
        }

}