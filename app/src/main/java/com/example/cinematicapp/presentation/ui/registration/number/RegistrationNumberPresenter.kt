package com.example.cinematicapp.presentation.ui.registration.number

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
class RegistrationNumberPresenter @Inject constructor() : MvpPresenter<RegistrationNumberView>() {

    private lateinit var mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var mAuth: FirebaseAuth

    fun authUser(phone: String) {
        mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                mAuth.signInWithCredential(p0).addOnCompleteListener {
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
}