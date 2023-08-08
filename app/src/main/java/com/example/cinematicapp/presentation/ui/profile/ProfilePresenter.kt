package com.example.cinematicapp.presentation.ui.profile

import com.example.cinematicapp.R
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.adapters.profile.ProfileModel
import com.example.cinematicapp.presentation.base.BasePresenter
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    private val pref: SharedPrefUseCase,
    private val firebase: FireBaseDataUseCase
) : BasePresenter<ProfileView>() {

    private var name: String? = null
    private var phone: String = ""
    private var uri: String? = null

    fun clickListener(item: ProfileModel) {
        when (item.name) {
            R.string.profile_information -> viewState.navigateToProfileInformation()
            R.string.profile_restore_pass -> viewState.navigateToNewPass()
            R.string.profile_notification -> viewState.navigateToNotifications()
            R.string.profile_report -> viewState.sendProblem()
            R.string.profile_logout -> {
                pref.addSignInUserStatus(false)
                viewState.signOutComplete()
            }
        }
    }

    fun getUserPhone(): String {
        phone = pref.getUserPhone()
        viewState.setUserPhone(phone)
        return phone
    }

    fun getUserName() {
        if (name != null) {
            setUserName()
        }
        firebase.getUserName(phone) {
            if (name != it) name = it
            setUserName()
        }
    }

    fun getUserPhoto() {
        if (uri != null) setUserPhoto(uri!!)
        firebase.getUserPhoto(phone) {
            if (it != null && uri != it) {
                uri = it
                setUserPhoto(it)
            }
        }
    }

    fun saveUserPhoto(uri: String) {
        firebase.addUserPhoto(phone, uri)
        setUserPhoto(uri)
    }

    private fun setUserName() {
        viewState.setUserName(name ?: "")
    }

    private fun setUserPhoto(uri: String) {
        viewState.setUserPhoto(uri)
    }
}