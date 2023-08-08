package com.example.cinematicapp.presentation.ui.profile.person

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.utils.ViewUtils.validate
import com.google.android.material.textfield.TextInputLayout
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class ProfilePersonPresenter @Inject constructor(
    private val firebase: FireBaseDataUseCase,
    private val pref: SharedPrefUseCase
) : BasePresenter<ProfilePersonView>() {

    fun getUserPhone() {
        viewState.addNewUser(pref.getUserPhone())
    }

    fun addNewUserName(phone: String, name: String, secondName: String) {
        firebase.addNewUserName(phone, name, secondName)
        viewState.addNewUserSuccess()
    }

    fun validateText(view: TextInputLayout, text: String) = view.validate(text)
}