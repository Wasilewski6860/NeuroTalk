package com.example.neurotalk.custom_ui

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.neurotalk.R

class ChatInputBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var sendButtonView: ImageView
    private var editTextView: EditText

    init {
        inflate(context, R.layout.chat_input_bar, this)
        sendButtonView = findViewById(R.id.chatInputBarSendMessageButton)
        editTextView = findViewById(R.id.chatInputBarEditTextView)
    }

    fun sendMessage(
        action: (String) -> Unit
    ) {
        val text = editTextView.text.toString()
        if (text.isNotEmpty()) {
            action(text)
        }
    }

    val messageText: String get() = editTextView.text.toString()

}