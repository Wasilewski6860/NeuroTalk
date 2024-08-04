package com.example.neurotalk.presentation.main.chat.feature

import android.os.Parcelable
import com.example.neurotalk.common.State
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChatState(
    val messages: State<List<String>>,
    val chatInfo: State<String>, //TODO Переделать, когда будет известно, что нужно для работы чата
) : Parcelable