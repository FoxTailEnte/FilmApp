package com.example.cinematicapp.repository.di.modules

import com.example.cinematicapp.repository.network.api.Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module (includes = [RetrofitModule::class]
)
class ApiModule {

    @Provides
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
}