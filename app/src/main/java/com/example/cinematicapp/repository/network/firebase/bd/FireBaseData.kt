package com.example.cinematicapp.repository.network.firebase.bd

import com.example.cinematicapp.repository.network.firebase.models.UserModel

interface FireBaseData {
    fun addNewUser(userModel: UserModel, phone: String)
    fun addNewPass(phone: String, pass: String)
    fun addNewUserName(phone: String, name:String,secondName: String)
    fun getUserName(phone: String, action: (String?) -> Unit)
}