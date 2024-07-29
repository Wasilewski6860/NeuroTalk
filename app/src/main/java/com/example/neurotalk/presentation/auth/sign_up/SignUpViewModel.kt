package com.example.neurotalk.presentation.auth.sign_up

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.base.BaseMviViewModel
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.RegisterUseCase
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

