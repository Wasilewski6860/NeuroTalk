package com.example.neurotalk.presentation.main.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.base.BaseMviViewModel
import com.example.neurotalk.presentation.main.home.feature.GettingUserAndChatsDataFeature
import com.example.neurotalk.presentation.main.home.feature.HomeDependencies
import com.example.neurotalk.presentation.main.home.feature.HomeMessage
import com.example.neurotalk.presentation.main.home.feature.HomeState
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    dependencies: HomeDependencies,
    savedStateHandle: SavedStateHandle
) : BaseMviViewModel<HomeState, HomeMessage, HomeDependencies>(
    init = GettingUserAndChatsDataFeature.initialUpdate,
    update = GettingUserAndChatsDataFeature::update,
    restore = GettingUserAndChatsDataFeature::restore,
    dependencies = dependencies,
    savedStateHandle = savedStateHandle
)