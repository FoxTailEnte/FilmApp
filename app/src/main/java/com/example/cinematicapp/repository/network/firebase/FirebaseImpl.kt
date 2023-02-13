package com.example.cinematicapp.repository.network.firebase

import android.util.Log
import android.widget.Toast
import com.example.cinematicapp.R
import com.example.cinematicapp.presentation.ui.autorization.login.LogInFragment
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class FirebaseImpl : FireBase {
    private lateinit var mDataBase: DatabaseReference

    override fun authUser(phone: String, pass: String) {
        /*var currentString:String = ""
        Log.d("MyLog", "1")
        mDataBase = FirebaseDatabase.getInstance().getReference(USERS)
        mDataBase.child(phone).get().addOnSuccessListener {
            if (it.value == null) {

            } else {
                val currentPass = it.child("pass").value
                if (currentPass.toString() == pass) {
                    currentString = "3"
                    Log.d("MyLog", "3")
                } else {
                    currentString = "4"
                    Log.d("MyLog", "4")
                }
            }
        }
        Log.d("MyLog", currentString)
        return currentString*/
    }

    companion object {
        const val USERS = "Users"
    }

}