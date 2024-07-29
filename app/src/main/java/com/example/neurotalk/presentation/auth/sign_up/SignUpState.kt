package com.example.neurotalk.presentation.auth.sign_up

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class SignUpState: Parcelable {
    @Parcelize
    data object NotStarted: SignUpState()
    @Parcelize
    data object Loading: SignUpState()
    @Parcelize
    data object Success: SignUpState()
    @Parcelize
    data object Error: SignUpState() //TODO Для разных ошибок добавить разных состояний
}