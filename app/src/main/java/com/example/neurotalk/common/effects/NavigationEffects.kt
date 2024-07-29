package com.example.neurotalk.common.effects

import com.example.base.coroutines.Effect
import com.example.neurotalk.navigation.Navigator
import com.example.neurotalk.navigation.Screen

object NavigationEffects {

    class Forward(screen: Screen) : Effect<Navigator, Unit> by Effect.onMain.idle({ navigator ->
        navigator.forward(screen)
    })


    object Back : Effect<Navigator, Unit> by Effect.onMain.idle({ navigator ->
        navigator.back()
    })

    // Also, BackTo,
}