package com.example.cinematicapp.presentation.ui.registration.person

import com.example.cinematicapp.repository.network.firebase.models.UserModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject


@InjectViewState
class RegistrationPersonInfoPresenter @Inject constructor() : MvpPresenter<RegistrationPersonInfoView>() {
    private lateinit var mDataBase: DatabaseReference

    fun addNewUser(userModel: UserModel, phone: String) {
        mDataBase = Firebase.database.reference
        mDataBase.child(USERS).child(phone).setValue(userModel)
        viewState.completeRegistration()
    }

    companion object {
        const val USERS = "Users"
    }
}