package com.example.neurotalk.presentation.auth.sign_up

import android.content.Context
import android.view.View
import com.example.base.coroutines.Effect
import com.example.base.coroutines.Update
import com.example.base.coroutines.adaptIdle
import com.example.base.coroutines.noEffects
import com.example.base.coroutines.with
import com.example.domain.coroutines.Response
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.neurotalk.R
import com.example.neurotalk.common.effects.NavigationEffects
import com.example.neurotalk.common.effects.SnackbarEffects
import com.example.neurotalk.navigation.Screen
import com.example.neurotalk.presentation.auth.AuthNavigator

object SignUpFeature {

    val initialUpdate: Update<SignUpState, SignUpMessage, SignUpDependencies> = SignUpState.NotStarted with noEffects<SignUpMessage, SignUpDependencies>()

    fun restore(state: SignUpState): Update<SignUpState, SignUpMessage, SignUpDependencies> =
        state with noEffects()

    fun update(message: SignUpMessage, state: SignUpState): Update<SignUpState, SignUpMessage, SignUpDependencies> =
        when (message) {
            is SignUpMessage.SignUpButtonClicked -> handleSignUpClick(message.name, message.email, message.password, message.confirmPassword)
            is SignUpMessage.SignUpResponse -> handleSignUpResponse(message.response)
        }

    private fun handleSignUpResponse(
        response: Response<String>
    ): Update<SignUpState, SignUpMessage, SignUpDependencies> =
        when(response) {
            is Response.Success -> SignUpState.Success with setOf(
                NavigationEffects.Forward(Screen.HomeScreen).adaptIdle { dependencies -> dependencies.navigator },
            )
            is Response.Failure -> SignUpState.Error with noEffects() //TODO Добавить снакбары в качестве сайдэффектов
        }

    private fun handleSignUpClick(name: String, email: String, password: String, confirmPassword: String): Update<SignUpState, SignUpMessage, SignUpDependencies> {
        return if(password != confirmPassword) SignUpState.Error with noEffects()
//                setOf(
//            SnackbarEffects.Show(R.string.confirmation_error) { Pair(context, rootView) }
//        )
        else SignUpState.Loading with setOf( Effects.SignUp(name, email, password) )
    }

}

object Effects {

    data class SignUp(val name: String, val email: String, val password: String) : Effect<SignUpDependencies, SignUpMessage> by Effect.single({ dependencies ->
        val result = dependencies.signUpUseCase(name = name, email = email, password = password)
        return@single SignUpMessage.SignUpResponse(result)
    })

}

class SignUpDependencies(
    val signUpUseCase: RegisterUseCase,
    val navigator: AuthNavigator,
    val context: Context,
//    val rootView: View
)