package com.example.cinematicapp.repository.network.firebase.bd

import com.example.cinematicapp.repository.network.firebase.models.UserModel
import com.example.cinematicapp.repository.utils.Constants
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FireBaseDataImpl : FireBaseData {
    private lateinit var mDataBase: DatabaseReference

    override fun addNewUser(userModel: UserModel, phone: String) {
        mDataBase = Firebase.database.reference
        mDataBase.child(Constants.USERS).child(phone).setValue(userModel)
    }

    override fun addNewPass(phone: String, pass: String) {
        mDataBase = Firebase.database.reference
        mDataBase.child(Constants.USERS).child(phone).child(Constants.PASS).setValue(pass)
    }
}