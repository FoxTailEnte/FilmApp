package com.example.cinematicapp.repository.network.firebase.sms

import android.app.Activity

interface FireBaseSms {
    fun authUser(phone: String, pass: String, action: (Boolean?) -> Unit)
    fun authUser(phone: String, action: (Boolean?) -> Unit)
    fun sentSms(phone: String, activity: Activity, action: (String) -> Unit)
    fun enterCode(id: String, code:String, action: (Boolean) -> Unit)
}