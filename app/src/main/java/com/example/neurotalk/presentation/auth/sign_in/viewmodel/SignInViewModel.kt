package com.example.neurotalk.presentation.auth.sign_in.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.BaseMviViewModel
import com.example.domain.usecase.auth.LoginUseCase
import com.example.neurotalk.presentation.auth.sign_in.feature.SignInDependencies
import com.example.neurotalk.presentation.auth.sign_in.feature.SignInFeature
import com.example.neurotalk.presentation.auth.sign_in.feature.SignInMessage
import com.example.neurotalk.presentation.auth.sign_in.feature.SignInState
import com.example.neurotalk.presentation.auth.sign_up.feature.SignUpDependencies
import com.example.neurotalk.presentation.auth.sign_up.feature.SignUpFeature
import com.example.neurotalk.presentation.auth.sign_up.feature.SignUpMessage
import com.example.neurotalk.presentation.auth.sign_up.feature.SignUpState
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    dependencies: SignInDependencies,
    savedStateHandle: SavedStateHandle
) : BaseMviViewModel<SignInState, SignInMessage, SignInDependencies>(
    init = SignInFeature.initialUpdate,
    update = SignInFeature::update,
    restore = SignInFeature::restore,
    dependencies = dependencies,
    savedStateHandle = savedStateHandle
)

