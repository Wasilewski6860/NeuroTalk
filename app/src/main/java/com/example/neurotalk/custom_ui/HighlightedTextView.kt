package com.example.neurotalk.custom_ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.example.neurotalk.R

class HighlightedTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var selectedWordStart = -1
    private var selectedWordEnd = -1
    private var text: SpannableStringBuilder
    private var textColor: Int

    private val paint = Paint()

    init {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.HighlightedTextView
        )
        textColor = typedArray.getColor(
            R.styleable.HighlightedTextView_textColor,
            ContextCompat.getColor(context, R.color.mistake_highlight_color)
        )
        typedArray.recycle()

        text = SpannableStringBuilder("Hello world!")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText(text.toString(), 0f, height.toFloat(), paint)
    }

    fun changeTextColor(
        start: Int,
        end: Int,
        color: Int = textColor
    ) {
        try {
            text.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        } catch (e: IndexOutOfBoundsException) {
            Log.e("text color change process", e.toString())
        }
    }

}