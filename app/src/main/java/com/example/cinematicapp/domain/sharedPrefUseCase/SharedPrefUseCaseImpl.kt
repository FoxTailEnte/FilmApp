package com.example.cinematicapp.domain.sharedPrefUseCase

import com.example.cinematicapp.repository.sharedPref.SaveUserAuthStatus
import javax.inject.Inject

class SharedPrefUseCaseImpl @Inject constructor(
    private val pref: SaveUserAuthStatus
): SharedPrefUseCase {

    override fun addSignInUserStatus(signIn: Boolean) {
        pref.addSignInUserStatus(signIn)
    }
    override fun getSignInUserStatus(): Boolean = pref.getSignInUserStatus()
}