package com.example.cinematicapp.repository.di.modules

import com.example.cinematicapp.repository.network.firebase.FireBase
import com.example.cinematicapp.repository.network.firebase.FirebaseImpl
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class FireBaseModule {

    @Provides
    fun provideFireBase(): FireBase = FirebaseImpl()
}