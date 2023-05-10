package com.example.cinematicapp.repository.network.firebase.bd

import com.example.cinematicapp.repository.network.firebase.models.UserModel
import com.example.cinematicapp.repository.utils.Constants
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FireBaseDataImpl : FireBaseData {
    private var myDataBase = Firebase.firestore

    override fun addNewUser(userModel: UserModel, phone: String) {
        myDataBase.collection(Constants.USERS).document(phone).set(userModel)
    }

    override fun addNewPass(phone: String, pass: String) {
        myDataBase.collection(Constants.USERS).document(phone).update(Constants.PASS, pass)
    }

    override fun addNewUserName(phone: String, name: String, secondName: String) {
        myDataBase.collection(Constants.USERS).document(phone).update(Constants.NAME, name)
        myDataBase.collection(Constants.USERS).document(phone).update(Constants.SECOND_NAME, secondName)
    }

    override fun getUserName(phone: String, action: (String?) -> Unit) {
        var name = ""
        var secondName: String
        myDataBase.collection(Constants.USERS).document(phone).collection(Constants.NAME).get().addOnSuccessListener {
            name = it.toString()
        }
        myDataBase.collection(Constants.USERS).document(phone).collection(Constants.SECOND_NAME).get()
            .addOnSuccessListener {
                secondName = it.toString()
                action.invoke("$name $secondName")
            }
    }

    override fun checkLibraryItem(phone: String, id: Int, action: (HashMap<String,Int>?) -> Unit) {
        myDataBase.collection(Constants.USERS).document(phone).get().addOnSuccessListener {
            val list: HashMap<String, Int>? = it.data?.get(Constants.LIBRARY) as HashMap<String, Int>?
            action.invoke(list)
        }
    }

    override fun checkWatchLaterItem(phone: String, id: Int, action: (HashMap<String, Int>?) -> Unit) {
        myDataBase.collection(Constants.USERS).document(phone).get().addOnSuccessListener {
            val list: HashMap<String, Int>? = it.data?.get(Constants.WATCH_LATER) as HashMap<String, Int>?
            action.invoke(list)
        }
    }

    override fun addToWatchLater(phone: String, film: HashMap<String, Int>) {
        myDataBase.collection(Constants.USERS).document(phone).update(Constants.WATCH_LATER, film)
    }

    override fun getWatchLater(phone: String, action: (HashMap<String, Int>?) -> Unit) {
        myDataBase.collection(Constants.USERS).document(phone).get().addOnSuccessListener {
            val list: HashMap<String, Int>? = it.data?.get(Constants.WATCH_LATER) as HashMap<String, Int>?
            action.invoke(list)
        }
    }

    override fun addToLibrary(phone: String, film: HashMap<String, Int>) {
        myDataBase.collection(Constants.USERS).document(phone).update(Constants.LIBRARY, film)
    }

    override fun getLibrary(phone: String, action: (HashMap<String, Int>?) -> Unit) {
        myDataBase.collection(Constants.USERS).document(phone).get().addOnSuccessListener {
            val list: HashMap<String, Int>? = it.data?.get(Constants.LIBRARY) as HashMap<String, Int>?
            action.invoke(list)
        }
    }
}