package com.example.neurotalk.presentation.main.chat.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.neurotalk.R
import com.example.neurotalk.databinding.ChatMessageCloudBinding

class ChatMessagesAdapter(
    private val listener: OnMessageClick,
    context: Context
) : RecyclerView.Adapter<ChatMessagesAdapter.ChatMessageHolder>() {

    private val isDarkThemeEnabled = when (
        context.resources.configuration.uiMode
                and
                android.content.res.Configuration.UI_MODE_NIGHT_MASK
    ) {
        android.content.res.Configuration.UI_MODE_NIGHT_NO -> false
        else -> true
    }

    // My favorite :)
    private val mockMessagesList = mutableListOf(
        TestMessageEntity("Hello, how are you doing?", TestMessageProducer.User),
        TestMessageEntity("Thanks for asking!\nI am great, how are you?", TestMessageProducer.Bot),
        TestMessageEntity("Hello, how are you doing?", TestMessageProducer.User),
        TestMessageEntity("Thanks for asking!\nI am great, how are you?", TestMessageProducer.Bot),
        TestMessageEntity("Hello, how are you doing?", TestMessageProducer.User),
        TestMessageEntity("Thanks for asking!\nI am great, how are you?", TestMessageProducer.Bot),
        TestMessageEntity("Hello, how are you doing?", TestMessageProducer.User),
        TestMessageEntity("Thanks for asking!\nI am great, how are you?", TestMessageProducer.Bot),
        TestMessageEntity("Hello, how are you doing?", TestMessageProducer.User),
        TestMessageEntity("Thanks for asking!\nI am great, how are you?", TestMessageProducer.Bot),
        TestMessageEntity("Hello, how are you doing?", TestMessageProducer.User),
        TestMessageEntity("Thanks for asking!\nI am great, how are you?", TestMessageProducer.Bot),
        TestMessageEntity("Hello, how are you doing?", TestMessageProducer.User),
        TestMessageEntity("Thanks for asking!\nI am great, how are you?", TestMessageProducer.Bot),
        TestMessageEntity("Hello, how are you doing?", TestMessageProducer.User),
        TestMessageEntity("Thanks for asking!\nI am great, how are you?", TestMessageProducer.Bot),
        TestMessageEntity("Hello, how are you doing?", TestMessageProducer.User),
        TestMessageEntity("Thanks for asking!\nI am great, how are you?", TestMessageProducer.Bot),
        TestMessageEntity("Hello, how are you doing?", TestMessageProducer.User),
        TestMessageEntity("Thanks for asking!\nI am great, how are you?", TestMessageProducer.Bot),
    )

    private val userMargins = mapOf(
        "left" to 160, "right" to 30
    )
    private val botMargins = mapOf(
        "left" to 30, "right" to 160
    )

    inner class ChatMessageHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ChatMessageCloudBinding.bind(view)
        private val botViewBackgroundColor = getBackgroundColorForBotMessagesBackground()

        /** Temp param for bind method, replace on ChatInfo or smth like that later **/
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(messageEntity: TestMessageEntity) = with(binding) {
            chatMessageCloudText.text = messageEntity.messageText

            val producer = messageEntity.producer

            val layoutParams = chatMessageCloudRootView.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.setMargins(
                when (producer) {
                    is TestMessageProducer.User -> userMargins["left"]!!
                    is TestMessageProducer.Bot -> botMargins["left"]!!
                },
                30,
                when (producer) {
                    is TestMessageProducer.User -> userMargins["right"]!!
                    is TestMessageProducer.Bot -> botMargins["right"]!!
                },
                30
            )
            chatMessageCloudRootView.apply {
                // TODO: replace getDrawable() method on another one
                background = context.resources.getDrawable(
                    when (producer) {
                        is TestMessageProducer.User -> R.drawable.user_message_cardview_background
                        is TestMessageProducer.Bot -> R.drawable.bot_message_cardview_background
                    }
                )
                setOnClickListener { listener.onMessageClick(adapterPosition) }

                // TODO: or remove this shit or think how use it properly
//                if (producer is TestMessageProducer.Bot) {
//                    setCardBackgroundColor(context.getColor(botViewBackgroundColor))
//                }
            }
        }
    }

    interface OnMessageClick {
        fun onMessageClick(adapterPosition: Int)
    }

    private fun getBackgroundColorForBotMessagesBackground(): Int {
        return if (isDarkThemeEnabled) {
            R.color.bot_message_background_night
        } else {
            R.color.bot_message_background_day
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.chat_message_cloud, parent, false
        )
        return ChatMessageHolder(view)
    }

    override fun getItemCount(): Int = mockMessagesList.size

    override fun onBindViewHolder(holder: ChatMessageHolder, position: Int) {
        holder.bind(mockMessagesList[position])
    }

    // Some abstract code for now
    fun addListOfMessagesToRecycler(params: Unit) {
        /** STUB! **/
    }

}

data class TestMessageEntity(
    val messageText: String,
    val producer: TestMessageProducer
)

sealed class TestMessageProducer {
    data object User : TestMessageProducer()
    data object Bot : TestMessageProducer()
}