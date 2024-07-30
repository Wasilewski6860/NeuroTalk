package com.example.neurotalk.presentation.auth.sign_in.feature

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignInState (
    val isLoading: Boolean
): Parcelable