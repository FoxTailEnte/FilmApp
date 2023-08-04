package com.example.cinematicapp.repository.network.firebase.bd

import com.example.cinematicapp.repository.network.firebase.models.UserModel

interface FireBaseData {
    fun addNewUser(userModel: UserModel, phone: String)
    fun addNewPass(phone: String, pass: String)
    fun addNewUserName(phone: String, name: String, secondName: String)
    fun getUserName(phone: String, action: (String?) -> Unit)
    fun checkLibraryItem(phone: String, id: Int, action: (HashMap<String, Int>?) -> Unit)
    fun checkWatchLaterItem(phone: String, id: Int, action: (HashMap<String, Int>?) -> Unit)
    fun updateWatchLater(phone: String, film: HashMap<String, Int>)
    fun getWatchLater(phone: String, action: (HashMap<String, Int>?) -> Unit)
    fun updateLibrary(phone: String, film: HashMap<String, Int>)
    fun addLibrary(phone: String, film: HashMap<String, Int>)
    fun addWatchLater(phone: String, film: HashMap<String, Int>)
    fun deleteLibItem(phone: String, filmList: HashMap<String, Int>)
    fun deleteWatchItem(phone: String, filmList: HashMap<String, Int>)
    fun getLibrary(phone: String, action: (HashMap<String, Int>?) -> Unit)
}