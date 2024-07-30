package com.example.neurotalk.presentation.auth.sign_up.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.base.BaseMviViewModel
import com.example.neurotalk.presentation.auth.sign_up.feature.SignUpDependencies
import com.example.neurotalk.presentation.auth.sign_up.feature.SignUpFeature
import com.example.neurotalk.presentation.auth.sign_up.feature.SignUpMessage
import com.example.neurotalk.presentation.auth.sign_up.feature.SignUpState
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    dependencies: SignUpDependencies,
    savedStateHandle: SavedStateHandle
) : BaseMviViewModel<SignUpState, SignUpMessage, SignUpDependencies>(
    init = SignUpFeature.initialUpdate,
    update = SignUpFeature::update,
    restore = SignUpFeature::restore,
    dependencies = dependencies,
    savedStateHandle = savedStateHandle
)

