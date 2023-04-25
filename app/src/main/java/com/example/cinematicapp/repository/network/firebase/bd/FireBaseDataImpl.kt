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
        myDataBase.collection(Constants.USERS).document(phone).collection(Constants.SECOND_NAME).get().addOnSuccessListener {
            secondName = it.toString()
            action.invoke("$name $secondName")
        }
    }

    override fun checkLibraryItem(phone:String, id: Int, action:(Boolean) -> Unit) {
        /*var currentString: Boolean
        myDataBase.collection(Constants.USERS).document(phone).collection(Constants.LIBRARY).child(id.toString()).get().addOnSuccessListener {
            currentString = it.value == null
            action.invoke(currentString)
        }*/
    }

    override fun checkWatchLaterItem(phone:String, id: Int, action:(Boolean) -> Unit) {
      /*  var currentString: Boolean
        myDataBase.child(Constants.USERS).child(phone).child(Constants.WATCH_LATER).child(id.toString()).get().addOnSuccessListener {
            currentString = it.value == null
            action.invoke(currentString)
        }*/
    }

    override fun addToWatchLater(phone: String, id: Int, name: String) {
       /* myDataBase.child(Constants.USERS).child(phone).child(Constants.WATCH_LATER).child(id.toString()).setValue(name)*/
    }

    override fun getWatchLater() {

    }

    override fun addToLibrary(phone: String, id: Int, name: String) {
/*
        myDataBase.child(Constants.USERS).child(phone).child(Constants.LIBRARY).child(id.toString()).setValue(name)
*/
    }

    override fun getLibrary() {

    }
}