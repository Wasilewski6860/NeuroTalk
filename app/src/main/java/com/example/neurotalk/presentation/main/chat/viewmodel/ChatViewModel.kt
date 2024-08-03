package com.example.neurotalk.presentation.main.chat.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.base.BaseMviViewModel
import com.example.neurotalk.presentation.main.chat.feature.ChatDependencies
import com.example.neurotalk.presentation.main.chat.feature.ChatFeature
import com.example.neurotalk.presentation.main.chat.feature.ChatMessage
import com.example.neurotalk.presentation.main.chat.feature.ChatState
import com.example.neurotalk.presentation.main.home.feature.GettingUserAndChatsDataFeature
import com.example.neurotalk.presentation.main.home.feature.HomeDependencies
import com.example.neurotalk.presentation.main.home.feature.HomeMessage
import com.example.neurotalk.presentation.main.home.feature.HomeState
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    dependencies: ChatDependencies,
    savedStateHandle: SavedStateHandle
) : BaseMviViewModel<ChatState, ChatMessage, ChatDependencies>(
    init = ChatFeature.initialUpdate,
    update = ChatFeature::update,
    restore = ChatFeature::restore,
    dependencies = dependencies,
    savedStateHandle = savedStateHandle
)