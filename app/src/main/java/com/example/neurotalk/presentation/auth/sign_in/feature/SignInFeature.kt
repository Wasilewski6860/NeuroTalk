package com.example.neurotalk.presentation.auth.sign_in.feature

import android.content.Context
import com.example.base.coroutines.Effect
import com.example.base.coroutines.Update
import com.example.base.coroutines.adaptIdle
import com.example.base.coroutines.noEffects
import com.example.base.coroutines.with
import com.example.domain.coroutines.Response
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.neurotalk.common.effects.NavigationEffects
import com.example.neurotalk.navigation.Screen
import com.example.neurotalk.presentation.auth.AuthNavigator

object SignInFeature {

    val initialUpdate: Update<SignInState, SignInMessage, SignInDependencies> = SignInState(isLoading = false) with noEffects<SignInMessage, SignInDependencies>()

    fun restore(state: SignInState): Update<SignInState, SignInMessage, SignInDependencies> =
        state with noEffects()

    fun update(message: SignInMessage, state: SignInState): Update<SignInState, SignInMessage, SignInDependencies> =
        when (message) {
            is SignInMessage.SignInButtonClicked -> handleSignInClick(message.email, message.password, state)
            is SignInMessage.SignInResponse -> handleSignInResponse(message.response, state)
        }

    private fun handleSignInResponse(
        response: Response<String>,
        state: SignInState
    ): Update<SignInState, SignInMessage, SignInDependencies> =
        when(response) {
            is Response.Success -> state.copy(isLoading = false) with setOf(
                NavigationEffects.Forward(Screen.HomeScreen).adaptIdle { dependencies -> dependencies.navigator },
            )
            is Response.Failure -> state.copy(isLoading = false) with noEffects() //TODO Добавить снакбары в качестве сайдэффектов
        }

    private fun handleSignInClick(email: String, password: String, state: SignInState): Update<SignInState, SignInMessage, SignInDependencies> {
       return state.copy(isLoading = true) with setOf(Effects.SignIn(email, password))
    }

}

object Effects {

    data class SignIn(val email: String, val password: String) : Effect<SignInDependencies, SignInMessage> by Effect.single({ dependencies ->
        val result = dependencies.signInUseCase(email = email, password = password)
        return@single SignInMessage.SignInResponse(result)
    })

}

class SignInDependencies(
    val signInUseCase: LoginUseCase,
    val navigator: AuthNavigator,
    val context: Context,
//    val rootView: View
)