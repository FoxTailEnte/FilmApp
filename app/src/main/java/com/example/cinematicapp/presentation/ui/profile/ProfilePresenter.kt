package com.example.cinematicapp.presentation.ui.profile

import com.example.cinematicapp.R
import com.example.cinematicapp.presentation.adapters.profile.ProfileModel
import com.example.cinematicapp.repository.data.sharedpref.SaveUserAuthStatus
import moxy.MvpPresenter
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    private val pref: SaveUserAuthStatus
) : MvpPresenter<ProfileView>() {

    fun singOutUser(item: ProfileModel) {
        if(item.name == R.string.profile_logout) {
            pref.addSignInUserStatus(false)
            viewState.signOutComplete()
        }
    }
}