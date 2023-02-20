package com.example.cinematicapp.presentation.ui.profile.pass.newPass

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class NewPassNewPassPresenter @Inject constructor(
    private val firebase: FireBaseDataUseCase
) : MvpPresenter<NewPassNewPassView>() {

    fun addNewPass(phone: String, pass: String) {
        firebase.addNewPass(phone,pass)
        viewState.setUserPass()
    }
}