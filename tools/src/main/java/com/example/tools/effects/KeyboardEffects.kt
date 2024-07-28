package com.example.tools.effects

import com.example.base.coroutines.Effect
import com.example.tools.android.KeyboardManager

object KeyboardEffects {
  object HideKeyboard : Effect<KeyboardManager, Unit> by Effect.onMain.idle({ keyboardManager ->
    keyboardManager.hideKeyboard()
  })
}
