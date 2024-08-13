package com.example.neurotalk.custom_ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.neurotalk.R

class MistakeDescriptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var mistakeTextView: TextView
    private var mistakeDescTextView: TextView
    private var imageView: ImageView

    // TODO: Discuss about it
    private var savedState = false

    init {
        inflate(context, R.layout.mistake_desc_item, this)
        mistakeTextView = findViewById(R.id.mistakeMessageTextView)
        mistakeDescTextView = findViewById(R.id.mistakeExplanationTextView)
        imageView = findViewById(R.id.addToDictionaryButton)
    }

    var mistakeText: String
        get() = mistakeTextView.text.toString()
        set(value) {
            mistakeTextView.text = value
        }

    var mistakeDescText: String
        get() = mistakeDescTextView.text.toString()
        set(value) {
            mistakeDescTextView.text = value
        }

    var saveButtonVisibility: Boolean
        get() = imageView.visibility == View.VISIBLE
        set(value) {
            when (value) {
                true -> imageView.visibility = View.VISIBLE
                false -> imageView.visibility = View.GONE
            }
        }

    fun save(
        onCheck: () -> Unit,
        onUncheck: () -> Unit
    ) {
        when (savedState) {
            true -> {
                imageView.setImageResource(R.drawable.bookmark_unfilled_icon)
                onUncheck()
                savedState = false
            }
            false -> {
                imageView.setImageResource(R.drawable.bookmark_filled_icon)
                onCheck()
                savedState = true
            }
        }
    }

}