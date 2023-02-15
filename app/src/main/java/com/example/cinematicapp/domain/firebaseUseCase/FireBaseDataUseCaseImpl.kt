package com.example.cinematicapp.domain.firebaseUseCase

import com.example.cinematicapp.repository.network.firebase.bd.FireBaseData
import com.example.cinematicapp.repository.network.firebase.models.UserModel
import javax.inject.Inject

class FireBaseDataUseCaseImpl @Inject constructor(
    private val firebase: FireBaseData
): FireBaseDataUseCase{
    override fun addNewUser(userModel: UserModel, phone: String) {
        firebase.addNewUser(userModel,phone)
    }

    override fun addNewPass(phone: String, pass: String) {
        firebase.addNewPass(phone,pass)
    }
}