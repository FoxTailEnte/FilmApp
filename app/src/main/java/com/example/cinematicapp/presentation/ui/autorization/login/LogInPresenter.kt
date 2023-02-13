package com.example.cinematicapp.presentation.ui.autorization.login

import com.example.cinematicapp.repository.utils.Constants
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class LogInPresenter @Inject constructor() : MvpPresenter<LogInView>() {
    private lateinit var mDataBase: DatabaseReference

    fun authUser(phone: String, pass: String) {
        mDataBase = FirebaseDatabase.getInstance().getReference(Constants.USERS)
        mDataBase.child(phone).get().addOnSuccessListener {
            if (it.value == null) {
                viewState.userNotFound()
            } else {
                val currentPass = it.child(Constants.PASS).value
                if (currentPass.toString() == pass) {
                    viewState.userAuthComplete()
                } else {
                    viewState.userDataError()
                }
            }
        }
    }
}
