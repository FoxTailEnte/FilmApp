package com.example.cinematicapp.domain.sharedPrefUseCase

interface SharedPrefUseCase {
    fun addSignInUserStatus(signIn: Boolean)
    fun getSignInUserStatus(): Boolean
    fun saveUserPhone(phone: String)
    fun getUserPhone(): String
}