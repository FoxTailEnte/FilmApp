package com.example.cinematicapp.presentation.ui.profile

import com.example.cinematicapp.R
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.adapters.profile.ProfileModel
import moxy.MvpPresenter
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    private val pref: SharedPrefUseCase,
    private val firebase: FireBaseDataUseCase
) : MvpPresenter<ProfileView>() {

    fun singOutUser(item: ProfileModel) {
        when (item.name ) {
            R.string.profile_information -> viewState.navigateToProfileInformation()
            R.string.profile_restore_pass -> viewState.navigateToNewPass()
            R.string.profile_notification -> Unit
            R.string.profile_report -> Unit
            R.string.profile_donation -> Unit
            R.string.profile_logout -> {
                pref.addSignInUserStatus(false)
                viewState.signOutComplete()
            }
        }
    }

    fun getUserPhone(): String = pref.getUserPhone()

    fun getUserName(phone: String, action: (String?) -> Unit) = firebase.getUserName(phone) {
        action.invoke(it)
    }
}