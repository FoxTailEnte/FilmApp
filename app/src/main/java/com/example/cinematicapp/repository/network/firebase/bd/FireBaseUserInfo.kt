package com.example.cinematicapp.repository.network.firebase.bd

import com.example.cinematicapp.repository.network.firebase.models.UserModel

interface FireBaseUserInfo {
    fun addNewUser(userModel: UserModel, phone: String)
}