package com.example.neurotalk.common

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar

object SnackbarManager {
    fun showSnackbar(text : String, view: View){
        Snackbar.make(
            view,
            text,
            Snackbar.LENGTH_LONG
        ).show()
    }
}