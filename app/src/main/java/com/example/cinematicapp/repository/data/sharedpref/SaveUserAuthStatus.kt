package com.example.cinematicapp.repository.data.sharedpref

interface SaveUserAuthStatus {
    fun addSignInUserStatus(signIn: Boolean)
    fun getSignInUserStatus(): Boolean
}