package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.number

import com.example.cinematicapp.repository.utils.Constants
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import moxy.InjectViewState
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class ForgotPasswordPresenter @Inject constructor() : MvpPresenter<ForgotPasswordView>() {
    private lateinit var mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDataBase: DatabaseReference


    fun checkUserPhone(phone: String) {
        mDataBase = FirebaseDatabase.getInstance().getReference(Constants.USERS)
        mDataBase.child(phone).get().addOnSuccessListener {
            if (it.value == null) {
                viewState.userNotRegister()
            } else {
                authUser(phone)
            }
        }
    }

    private fun authUser(phone: String) {
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
}