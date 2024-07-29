package com.example.neurotalk.di.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthRootView

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainRootView
