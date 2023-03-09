package com.example.cinematicapp.presentation.ui.profile.notifications

import android.os.Bundle
import android.view.View
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.databinding.FragmentNotificationsBinding
import com.example.cinematicapp.databinding.FragmentProfileBinding
import com.example.cinematicapp.presentation.adapters.profile.ProfileAdapter
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.getMainActivityView
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class NotificationsFragment: BaseFragment<FragmentNotificationsBinding, NotificationsView, NotificationsPresenter>(), NotificationsView {

    @InjectPresenter
    lateinit var presenter: NotificationsPresenter

    @ProvidePresenter
    fun providePresenter() = appComponent.provideNotificationPresenter()

    override fun initializeBinding() = FragmentNotificationsBinding.inflate(layoutInflater)

}