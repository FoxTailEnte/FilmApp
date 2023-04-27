package com.example.cinematicapp.repository.network.firebase.bd

import com.example.cinematicapp.repository.network.firebase.models.UserModel

interface FireBaseData {
    fun addNewUser(userModel: UserModel, phone: String)
    fun addNewPass(phone: String, pass: String)
    fun addNewUserName(phone: String, name:String,secondName: String)
    fun getUserName(phone: String, action: (String?) -> Unit)
    fun checkLibraryItem(phone:String, id: Int, action:(List<Int>?) -> Unit)
    fun checkWatchLaterItem(phone:String, id: Int, action:(List<Int>?) -> Unit)
    fun addToWatchLater(phone: String, film: List<Any?>)
    fun getWatchLater(phone: String, id: Int, action: (List<Int>?) -> Unit)
    fun addToLibrary(phone: String, film: List<Any?>)
    fun getLibrary(phone: String, action: (ArrayList<String>?) -> Unit)
}