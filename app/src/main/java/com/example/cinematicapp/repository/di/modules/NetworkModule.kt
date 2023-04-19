package com.example.cinematicapp.repository.di.modules

import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCase
import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCaseImpl
import com.example.cinematicapp.repository.network.PassengerDataSourceImpl
import com.example.cinematicapp.repository.network.PassengerSource
import com.example.cinematicapp.repository.network.PassengersRepos
import com.example.cinematicapp.repository.network.api.Api
import com.example.cinematicapp.repository.network.api.GetHomeFilms
import com.example.cinematicapp.repository.network.api.GetHomeFilmsImpl
import dagger.Module
import dagger.Provides

@Module(
includes =[ApiModule::class]
)
class NetworkModule {

    @Provides
    fun provideHomeFilmUseCase(getHomeFilms: GetHomeFilms): GetHomeFilmsUseCase = GetHomeFilmsUseCaseImpl(getHomeFilms)

    @Provides
    fun provideHomeFilmApi(api: Api): GetHomeFilms = GetHomeFilmsImpl(api)

    @Provides
    fun providePassengerRepos(getHomeFilmsUseCase: GetHomeFilmsUseCase): PassengersRepos = PassengersRepos(getHomeFilmsUseCase)

    @Provides
    fun providePassengerSource(passengerRepos: PassengersRepos): PassengerSource = PassengerDataSourceImpl(passengerRepos)
}