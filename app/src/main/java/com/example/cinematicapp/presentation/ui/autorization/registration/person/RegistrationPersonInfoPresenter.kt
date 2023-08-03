package com.example.cinematicapp.presentation.ui.autorization.registration.person

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.network.firebase.models.UserModel
import com.example.cinematicapp.repository.utils.ViewUtils.validate
import com.google.android.material.textfield.TextInputLayout
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class RegistrationPersonInfoPresenter @Inject constructor(
    private val firebase: FireBaseDataUseCase
) : BasePresenter<RegistrationPersonInfoView>() {

    fun addNewUser(userModel: UserModel, phone: String) {
        firebase.addNewUser(userModel, phone)
        viewState.completeRegistration()
    }

    fun validateText(view: TextInputLayout, text: String) = view.validate(text)
}