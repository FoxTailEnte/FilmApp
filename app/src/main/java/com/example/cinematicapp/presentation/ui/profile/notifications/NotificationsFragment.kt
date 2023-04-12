package com.example.cinematicapp.presentation.ui.profile.notifications

import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.databinding.FragmentNotificationsBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class NotificationsFragment: BaseFragment<FragmentNotificationsBinding, NotificationsView, NotificationsPresenter>(), NotificationsView {

    @InjectPresenter
    lateinit var presenter: NotificationsPresenter

    @ProvidePresenter
    fun providePresenter() = appComponent.provideNotificationPresenter()

    override fun initializeBinding() = FragmentNotificationsBinding.inflate(layoutInflater)
    override fun setLoadingState(isLoading: Boolean) {
        TODO("Not yet implemented")
    }

}