package com.example.cinematicapp.repository.network.firebase.bd

import android.app.Activity
import com.example.cinematicapp.repository.utils.Constants
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.TimeUnit


class FirebaseSmsImpl : FireBaseSms {
    private lateinit var mDataBase: DatabaseReference
    private lateinit var mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var mAuth: FirebaseAuth

    override fun authUser(phone: String, pass: String, action: (Boolean?) -> Unit) {
        var currentString: Boolean? = null
        mDataBase = FirebaseDatabase.getInstance().getReference(Constants.USERS)
        mDataBase.child(phone).get().addOnSuccessListener {
            if (it.value != null) {
                val currentPass = it.child(Constants.PASS).value
                currentString = currentPass.toString() == pass
            }
            action.invoke(currentString)
        }
    }

    override fun authUser(phone: String, action: (Boolean) -> Unit) {
        var currentString: Boolean
        mDataBase = FirebaseDatabase.getInstance().getReference(Constants.USERS)
        mDataBase.child(phone).get().addOnSuccessListener {
            currentString = it.value == null
            action.invoke(currentString)
        }
    }

    override fun sentSms(phone: String, activity: Activity, action: (String) -> Unit) {
        var currentString: String
        mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                mAuth.signInWithCredential(p0).addOnCompleteListener {
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                currentString = Constants.FAIL
                action.invoke(currentString)
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                currentString = id
                action.invoke(currentString)
            }
        }
        mAuth = FirebaseAuth.getInstance()
        val option = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(phone)
            .setTimeout(60, TimeUnit.SECONDS)
            .setCallbacks(mCallBack)
            .setActivity(activity)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(option)
    }

    override fun enterCode(id: String, code:String, action: (Boolean) -> Unit) {
        var currentString: Boolean
        mAuth = FirebaseAuth.getInstance()
        val credential = PhoneAuthProvider.getCredential(id, code)
        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            currentString = task.isSuccessful
            action.invoke(currentString)
        }
    }
}