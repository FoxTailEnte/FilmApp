package com.example.cinematicapp.domain.firebaseUseCase

import com.example.cinematicapp.repository.network.firebase.bd.FireBaseData
import com.example.cinematicapp.repository.network.firebase.models.UserModel
import javax.inject.Inject

class FireBaseDataUseCaseImpl @Inject constructor(
    private val firebase: FireBaseData
) : FireBaseDataUseCase {
    override fun addNewUser(userModel: UserModel, phone: String) {
        firebase.addNewUser(userModel, phone)
    }

    override fun addNewPass(phone: String, pass: String) {
        firebase.addNewPass(phone, pass)
    }

    override fun addNewUserName(phone: String, name: String, secondName: String) {
        firebase.addNewUserName(phone, name, secondName)
    }

    override fun getUserName(phone: String, action: (String?) -> Unit) =
        firebase.getUserName(phone) {
            action.invoke(it)
        }

    override fun checkLibraryItem(phone: String, id: Int, action: (HashMap<String, Int>?) -> Unit) {
        firebase.checkLibraryItem(phone, id) {
            action.invoke(it)
        }
    }

    override fun checkWatchLaterItem(
        phone: String,
        id: Int,
        action: (HashMap<String, Int>?) -> Unit
    ) {
        firebase.checkWatchLaterItem(phone, id) {
            action.invoke(it)
        }
    }

    override fun addToWatchLater(phone: String, film: HashMap<String, Int>) {
        firebase.addToWatchLater(phone, film)
    }

    override fun getWatchLater(phone: String, action: (HashMap<String, Int>?) -> Unit) {
        firebase.getWatchLater(phone) {
            action.invoke(it)
        }
    }

    override fun addToLibrary(phone: String, filmList: HashMap<String, Int>) {
        firebase.addToLibrary(phone, filmList)
    }

    override fun getLibrary(phone: String, action: (HashMap<String, Int>?) -> Unit) {
        firebase.getLibrary(phone) {
            action.invoke(it)
        }
    }
}