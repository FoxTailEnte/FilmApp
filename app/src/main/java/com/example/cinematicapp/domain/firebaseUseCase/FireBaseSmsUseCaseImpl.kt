package com.example.cinematicapp.domain.firebaseUseCase

import android.app.Activity
import com.example.cinematicapp.repository.network.firebase.bd.FireBaseSms
import javax.inject.Inject


class FireBaseSmsUseCaseImpl @Inject constructor(
    private val firebase: FireBaseSms
) : FireBaseSmsUseCase {

    override fun authUser(phone: String, pass: String, action: (Boolean?) -> Unit) {
        firebase.authUser(phone,pass) {
            action.invoke(it)
        }
    }

    override fun authUser(phone: String, action: (Boolean) -> Unit) {
        firebase.authUser(phone) {
            action.invoke(it)
        }
    }

    override fun sentSms(phone: String, activity: Activity, action: (String) -> Unit) {
        firebase.sentSms(phone,activity) {
            action.invoke(it)
        }
    }

    override fun enterCode(id: String, code:String, action: (Boolean) -> Unit) {
       firebase.enterCode(id,code) {
           action.invoke(it)
       }
    }
}