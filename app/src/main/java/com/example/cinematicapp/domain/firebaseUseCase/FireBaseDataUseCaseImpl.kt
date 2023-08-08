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

    override fun addUserPhoto(phone: String, uri: String) {
        firebase.addUserPhoto(phone, uri)
    }

    override fun getUserPhoto(phone: String, action: (String?) -> Unit) {
        firebase.getUserPhoto(phone) {
            action.invoke(it)
        }
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

    override fun updateWatchLater(phone: String, film: HashMap<String, Int>) {
        firebase.updateWatchLater(phone, film)
    }

    override fun getWatchLater(phone: String, action: (HashMap<String, Int>?) -> Unit) {
        firebase.getWatchLater(phone) {
            action.invoke(it)
        }
    }

    override fun addLibrary(phone: String, film: HashMap<String, Int>) {
        firebase.addLibrary(phone, film)
    }

    override fun addWatchLater(phone: String, film: HashMap<String, Int>) {
        firebase.addWatchLater(phone, film)
    }

    override fun updateLibrary(phone: String, filmList: HashMap<String, Int>) {
        firebase.updateLibrary(phone, filmList)
    }

    override fun deleteLibItem(phone: String, filmList: HashMap<String, Int>) {
        firebase.deleteLibItem(phone, filmList)
    }

    override fun deleteWatchItem(phone: String, filmList: HashMap<String, Int>) {
        firebase.deleteWatchItem(phone, filmList)
    }

    override fun getLibrary(phone: String, action: (HashMap<String, Int>?) -> Unit) {
        firebase.getLibrary(phone) {
            action.invoke(it)
        }
    }
}