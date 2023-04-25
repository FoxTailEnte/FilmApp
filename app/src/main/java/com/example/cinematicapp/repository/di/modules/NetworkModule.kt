package com.example.cinematicapp.repository.di.modules

import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCase
import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCaseImpl
import com.example.cinematicapp.repository.network.parsHome.PassengerDataSourceImpl
import com.example.cinematicapp.repository.network.parsHome.PassengerSource
import com.example.cinematicapp.repository.network.parsHome.PassengersRepos
import com.example.cinematicapp.repository.network.api.Api
import com.example.cinematicapp.repository.network.api.GetHomeFilms
import com.example.cinematicapp.repository.network.api.GetHomeFilmsImpl
import com.example.cinematicapp.repository.network.parsLibrary.PassengerLibraryDataSourceImpl
import com.example.cinematicapp.repository.network.parsLibrary.PassengerLibrarySource
import com.example.cinematicapp.repository.network.parsLibrary.PassengersLibraryRepos
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

    @Provides
    fun providePassengerLibraryRepos(getHomeFilmsUseCase: GetHomeFilmsUseCase): PassengersLibraryRepos = PassengersLibraryRepos(getHomeFilmsUseCase)

    @Provides
    fun providePassengerLibrarySource(passengerLibraryRepos: PassengersLibraryRepos): PassengerLibrarySource = PassengerLibraryDataSourceImpl(passengerLibraryRepos)

}