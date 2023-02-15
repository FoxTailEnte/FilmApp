package com.example.cinematicapp.repository.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.example.cinematicapp.repository.utils.Constants
import javax.inject.Inject

class SaveUserAuthStatusImpl @Inject constructor(
    private val context: Context
): SaveUserAuthStatus {
    private var pref: SharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
    private lateinit var editor:SharedPreferences.Editor

    override fun addSignInUserStatus(signIn: Boolean) {
        editor = pref.edit()
        editor.putBoolean(SIGN_IN,signIn)
        editor.apply()
    }

    override fun getSignInUserStatus(): Boolean = pref.getBoolean(SIGN_IN, false)

    override fun saveUserPhone(phone: String) {
        editor = pref.edit()
        editor.putString(PHONE,phone)
        editor.apply()
    }

    override fun getUserPhone(): String = pref.getString(PHONE, "")!!

    companion object {
        const val SIGN_IN = "sign_in"
        const val PHONE = "phone"
    }
}