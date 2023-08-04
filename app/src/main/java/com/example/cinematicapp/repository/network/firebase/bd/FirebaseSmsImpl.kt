package com.example.cinematicapp.repository.network.firebase.bd

import android.app.Activity
import com.example.cinematicapp.repository.utils.Constants
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


class FirebaseSmsImpl : FireBaseSms {
    private var myDataBase = Firebase.firestore
    private lateinit var mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var mAuth: FirebaseAuth

    override fun authUser(phone: String, pass: String, action: (Boolean?) -> Unit) {
        var currentString: Boolean? = null
        myDataBase.collection(Constants.FireBase.USERS).document(phone).get().addOnSuccessListener {
            if (it != null) {
                currentString = it.get(Constants.FireBase.PASS).toString() == pass
            }
            action.invoke(currentString)
        }
    }

    override fun authUser(phone: String, action: (Boolean) -> Unit) {
        var contains = false
        myDataBase.collection(Constants.FireBase.USERS).get().addOnSuccessListener { it ->
            it.documents.forEach {
                if(it.id == phone) {
                    contains = true
                }
            }
        }.addOnCompleteListener {
            action.invoke(contains)
        }
    }

    override fun sentSms(phone: String, activity: Activity, action: (String) -> Unit) {
        var currentString: String
        mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(p0: FirebaseException) {
                currentString = Constants.FireBase.FAIL
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

    override fun enterCode(id: String, code: String, action: (Boolean) -> Unit) {
        var currentString: Boolean
        mAuth = FirebaseAuth.getInstance()
        val credential = PhoneAuthProvider.getCredential(id, code)
        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            currentString = task.isSuccessful
            action.invoke(currentString)
        }
    }
}