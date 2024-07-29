package com.example.neurotalk.common.effects

import android.content.Context
import android.view.View
import com.example.base.coroutines.Effect
import com.example.neurotalk.common.SnackbarManager
import com.example.neurotalk.common.SnackbarManager.showSnackbar
import com.example.neurotalk.navigation.Navigator

object SnackbarEffects {

    data class Show<Dependencies, out Message>(
        val textRes: Int,
        val contextViewProvider: Dependencies.() -> Pair<Context, View>
    ) : Effect<Dependencies, Message> by Effect.onMain.idle({ dependencies ->
        showSnackbar(dependencies.contextViewProvider().first.getString(textRes), dependencies.contextViewProvider().second)
    })
}
