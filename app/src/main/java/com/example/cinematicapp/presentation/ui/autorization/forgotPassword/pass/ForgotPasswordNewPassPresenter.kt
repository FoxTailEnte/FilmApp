package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.pass

import com.example.cinematicapp.repository.utils.Constants
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ForgotPasswordNewPassPresenter @Inject constructor() : MvpPresenter<ForgotPasswordNewPassView>() {
    private lateinit var mDataBase: DatabaseReference

    fun addNewPass(phone: String, pass: String) {
        mDataBase = Firebase.database.reference
        mDataBase.child(Constants.USERS).child(phone).child("pass").setValue(pass)
        viewState.setUserPass()
    }
}