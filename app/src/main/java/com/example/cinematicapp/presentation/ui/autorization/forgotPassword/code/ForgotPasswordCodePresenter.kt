package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.code

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import moxy.InjectViewState
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class ForgotPasswordCodePresenter @Inject constructor() : MvpPresenter<ForgotPasswordCodeView>() {
    private lateinit var mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var mAuth: FirebaseAuth

    fun authUser(phone: String) {
        mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                mAuth.signInWithCredential(p0).addOnCompleteListener {
                    Unit
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                viewState.verificationFailed()
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                viewState.sentCodeSuccess(phone, id)
            }
        }
        mAuth = FirebaseAuth.getInstance()
        val option = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(phone)
            .setTimeout(60, TimeUnit.SECONDS)
            .setCallbacks(mCallBack)
        viewState.sentCode(option)
    }

    fun enterCode(id: String, code: String) {
        mAuth = FirebaseAuth.getInstance()
        val credential = PhoneAuthProvider.getCredential(id, code)
        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                viewState.confirmCodeSuccessToast()
            } else {
                viewState.confirmCodeFailToast()
            }
        }
    }
}