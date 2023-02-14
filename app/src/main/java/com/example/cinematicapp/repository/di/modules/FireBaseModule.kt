package com.example.cinematicapp.repository.di.modules

import com.example.cinematicapp.repository.network.firebase.bd.FireBaseUserInfo
import com.example.cinematicapp.repository.network.firebase.bd.FireBaseUserInfoImpl
import com.example.cinematicapp.repository.network.firebase.sms.FireBaseSms
import com.example.cinematicapp.repository.network.firebase.sms.FirebaseSmsImpl
import dagger.Module
import dagger.Provides

@Module
class FireBaseModule {

    @Provides
    fun provideFireBase(): FireBaseSms = FirebaseSmsImpl()

    @Provides
    fun provideFireBaseUserInfo(): FireBaseUserInfo = FireBaseUserInfoImpl()
}