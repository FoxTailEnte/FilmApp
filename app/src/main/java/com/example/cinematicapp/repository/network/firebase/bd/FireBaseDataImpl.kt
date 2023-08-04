package com.example.cinematicapp.repository.network.firebase.bd

import com.example.cinematicapp.repository.network.firebase.models.UserModel
import com.example.cinematicapp.repository.utils.Constants
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FireBaseDataImpl : FireBaseData {
    private var myDataBase = Firebase.firestore

    override fun addNewUser(userModel: UserModel, phone: String) {
        myDataBase.collection(Constants.FireBase.USERS).document(phone).set(userModel)
    }

    override fun addNewPass(phone: String, pass: String) {
        myDataBase.collection(Constants.FireBase.USERS).document(phone)
            .update(Constants.FireBase.PASS, pass)
    }

    override fun addNewUserName(phone: String, name: String, secondName: String) {
        myDataBase.collection(Constants.FireBase.USERS).document(phone)
            .update(Constants.FireBase.NAME, name)
        myDataBase.collection(Constants.FireBase.USERS).document(phone)
            .update(Constants.FireBase.SECOND_NAME, secondName)
    }

    override fun getUserName(phone: String, action: (String?) -> Unit) {
        var name = ""
        var secondName: String
        myDataBase.collection(Constants.FireBase.USERS).document(phone)
            .collection(Constants.FireBase.NAME)
            .get()
            .addOnSuccessListener {
                name = it.toString()
            }
        myDataBase.collection(Constants.FireBase.USERS).document(phone)
            .collection(Constants.FireBase.SECOND_NAME)
            .get()
            .addOnSuccessListener {
                secondName = it.toString()
                action.invoke("$name $secondName")
            }
    }

    override fun checkLibraryItem(phone: String, id: Int, action: (HashMap<String, Int>?) -> Unit) {
        myDataBase.collection(Constants.FireBase.USERS).document(phone).get().addOnSuccessListener {
            val list: HashMap<String, Int>? =
                it.data?.get(Constants.FireBase.LIBRARY) as HashMap<String, Int>?
            action.invoke(list)
        }
    }

    override fun checkWatchLaterItem(
        phone: String,
        id: Int,
        action: (HashMap<String, Int>?) -> Unit
    ) {
        myDataBase.collection(Constants.FireBase.USERS).document(phone).get().addOnSuccessListener {
            val list: HashMap<String, Int>? =
                it.data?.get(Constants.FireBase.WATCH_LATER) as HashMap<String, Int>?
            action.invoke(list)
        }
    }

    override fun updateWatchLater(phone: String, film: HashMap<String, Int>) {
        myDataBase.collection(Constants.FireBase.USERS).document(phone)
            .update(Constants.FireBase.WATCH_LATER, film)
    }

    override fun getWatchLater(phone: String, action: (HashMap<String, Int>?) -> Unit) {
        myDataBase.collection(Constants.FireBase.USERS).document(phone).get().addOnSuccessListener {
            val list: HashMap<String, Int>? =
                it.data?.get(Constants.FireBase.WATCH_LATER) as HashMap<String, Int>?
            action.invoke(list)
        }
    }

    override fun updateLibrary(phone: String, film: HashMap<String, Int>) {
        myDataBase.collection(Constants.FireBase.USERS).document(phone)
            .update(Constants.FireBase.LIBRARY, film)
    }

    override fun addLibrary(phone: String, film: HashMap<String, Int>) {
        myDataBase.collection(Constants.FireBase.USERS).document(phone)
            .update(Constants.FireBase.LIBRARY, film)
    }

    override fun addWatchLater(phone: String, film: HashMap<String, Int>) {
        myDataBase.collection(Constants.FireBase.USERS).document(phone)
            .update(Constants.FireBase.WATCH_LATER, film)
    }

    override fun deleteLibItem(phone: String, filmList: HashMap<String, Int>) {
        myDataBase.collection(Constants.FireBase.USERS).document(phone)
            .update(Constants.FireBase.LIBRARY, filmList)
    }

    override fun deleteWatchItem(phone: String, filmList: HashMap<String, Int>) {
        myDataBase.collection(Constants.FireBase.USERS).document(phone)
            .update(Constants.FireBase.WATCH_LATER, filmList)
    }

    override fun getLibrary(phone: String, action: (HashMap<String, Int>?) -> Unit) {
        myDataBase.collection(Constants.FireBase.USERS).document(phone).get().addOnSuccessListener {
            val list: HashMap<String, Int>? =
                it.data?.get(Constants.FireBase.LIBRARY) as HashMap<String, Int>?
            action.invoke(list)
        }
    }
}