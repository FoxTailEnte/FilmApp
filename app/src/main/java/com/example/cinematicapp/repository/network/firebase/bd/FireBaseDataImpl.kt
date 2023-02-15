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

    override fun addNewUserName(phone: String, name: String, secondName: String) {
        mDataBase = Firebase.database.reference
        mDataBase.child(Constants.USERS).child(phone).child(Constants.NAME).setValue(name)
        mDataBase.child(Constants.USERS).child(phone).child(Constants.SECOND_NAME).setValue(secondName)
    }

    override fun getUserName(phone: String, action: (String?) -> Unit) {
        var name = ""
        var secondName: String
        mDataBase = Firebase.database.reference
        mDataBase.child(Constants.USERS).child(phone).child(Constants.NAME).get().addOnSuccessListener {
            name = it.value.toString()
        }
        mDataBase.child(Constants.USERS).child(phone).child(Constants.SECOND_NAME).get().addOnSuccessListener {
            secondName = it.value.toString()
            action.invoke("$name $secondName")
        }
    }
}