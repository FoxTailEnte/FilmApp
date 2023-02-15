package com.example.cinematicapp.presentation.ui.registration.person

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.repository.network.firebase.models.UserModel
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject


@InjectViewState
class RegistrationPersonInfoPresenter @Inject constructor(
    private val firebase: FireBaseDataUseCase
) : MvpPresenter<RegistrationPersonInfoView>() {

    fun addNewUser(userModel: UserModel, phone: String) {
        firebase.addNewUser(userModel, phone)
        viewState.completeRegistration()
    }
}