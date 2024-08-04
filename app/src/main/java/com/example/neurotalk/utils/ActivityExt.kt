package com.example.neurotalk.utils

import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.setupKeyboardHidingListener(
    focusRemovingContainer: View, ev: MotionEvent?
) {
    if (ev?.action == MotionEvent.ACTION_DOWN) {
        val focus = currentFocus
        if (focus is EditText) {
            val outRect = Rect()
            focus.getGlobalVisibleRect(outRect)
            if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                KeyboardUtils.hideKeyboard(this, focusRemovingContainer)
            }
        }
    }
}