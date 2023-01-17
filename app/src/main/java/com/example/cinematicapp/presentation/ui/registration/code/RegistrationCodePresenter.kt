package com.example.cinematicapp.presentation.ui.registration.code

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject


@InjectViewState
class RegistrationCodePresenter @Inject constructor() : MvpPresenter<RegistrationCodeView>() {
    private lateinit var mAuth: FirebaseAuth

    fun enterCode(id: String, code: String) {
        mAuth = FirebaseAuth.getInstance()
        Log.d("MyLog","$id, $code")
        val cid = id
        val ccode = code
        val credential = PhoneAuthProvider.getCredential(cid, ccode)
        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                viewState.confirmCodeSuccess()
            } else {
                viewState.confirmCodeFailToast()
            }
        }
    }
}