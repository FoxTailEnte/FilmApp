package com.example.cinematicapp.presentation.ui.profile.notifications

import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.databinding.FragmentNotificationsBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.navigateBack
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class NotificationsFragment :
    BaseFragment<FragmentNotificationsBinding, NotificationsView, NotificationsPresenter>(),
    NotificationsView {


    @InjectPresenter
    lateinit var presenter: NotificationsPresenter

    override fun initializeBinding() = FragmentNotificationsBinding.inflate(layoutInflater)

    override fun setupListener() = with(binding) {
        btBackPress.setOnClickListener {
            navigateBack()
        }
    }

    override fun setLoadingState(isLoading: Boolean) {

    }

    @ProvidePresenter
    fun providePresenter() = appComponent.provideNotificationPresenter()
}