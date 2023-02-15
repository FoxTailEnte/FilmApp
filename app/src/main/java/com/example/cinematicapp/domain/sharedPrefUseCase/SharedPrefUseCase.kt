package com.example.cinematicapp.domain.sharedPrefUseCase

interface SharedPrefUseCase {
    fun addSignInUserStatus(signIn: Boolean)
    fun getSignInUserStatus(): Boolean
}