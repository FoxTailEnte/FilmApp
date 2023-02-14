package com.example.cinematicapp.repository.network.firebase

import android.app.Activity

interface FireBase {
    fun authUser(phone: String, pass: String, action: (Boolean?) -> Unit)
    fun authUser(phone: String, action: (Boolean?) -> Unit)
    fun sentSms(phone: String, activity: Activity, action: (String) -> Unit)
}