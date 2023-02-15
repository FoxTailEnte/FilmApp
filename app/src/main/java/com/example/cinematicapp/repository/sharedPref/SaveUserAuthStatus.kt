package com.example.cinematicapp.repository.sharedPref

interface SaveUserAuthStatus {
    fun addSignInUserStatus(signIn: Boolean)
    fun getSignInUserStatus(): Boolean
    fun saveUserPhone(phone: String)
    fun getUserPhone(): String
}