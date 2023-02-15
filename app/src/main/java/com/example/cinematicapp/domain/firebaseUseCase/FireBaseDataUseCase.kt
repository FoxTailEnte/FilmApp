package com.example.cinematicapp.domain.firebaseUseCase

import com.example.cinematicapp.repository.network.firebase.models.UserModel

interface FireBaseDataUseCase {
    fun addNewUser(userModel: UserModel, phone: String)
    fun addNewPass(phone: String, pass: String)
    fun addNewUserName(phone: String,name:String,secondName: String)
    fun getUserName(phone: String, action: (String?) -> Unit)
}