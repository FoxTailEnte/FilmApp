package com.example.cinematicapp.presentation.ui.profile

import android.os.Bundle
import android.view.View
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.databinding.FragmentProfileBinding
import com.example.cinematicapp.presentation.adapters.profile.ProfileAdapter
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileView,
        ProfilePresenter>(), ProfileView {
    private lateinit var adapter: ProfileAdapter


    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    override fun initializeBinding() = FragmentProfileBinding.inflate(layoutInflater)

    override fun setupUi() {
    }

    override fun setupListener() = with(binding) {
        val phone = presenter.getUserPhone()
        presenter.getUserName(phone) {
            tvName.text = it.toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRc()
    }

    override fun signOutComplete() {
        navigateTo(ProfileFragmentDirections.actionProfileFragmentToGraphAuthorization())
    }

    override fun navigateToProfileInformation() {
        navigateTo(ProfileFragmentDirections.actionProfileFragmentToProfilePersonFragment())
    }

    override fun navigateToNewPass() {
        navigateTo(ProfileFragmentDirections.actionProfileFragmentToNewPassNumberFragment())
    }

    override fun navigateToNotifications() {
        navigateTo(ProfileFragmentDirections.actionProfileFragmentToNotificationsFragment())
    }

    override fun setLoadingState(isLoading: Boolean) {}

    @ProvidePresenter
    fun providePresenter() = appComponent.provideProfilePresenter()

    private fun initRc() {
        adapter = ProfileAdapter { presenter.singOutUser(it) }
        binding.rcProfile.adapter = adapter
    }
}