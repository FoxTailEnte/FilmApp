package com.example.cinematicapp.presentation.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.databinding.FragmentProfileBinding
import com.example.cinematicapp.presentation.adapters.profile.ProfileAdapter
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.presentation.ui.main.MainActivity
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class ProfileFragment: BaseFragment<FragmentProfileBinding>(), ProfileView {
    private lateinit var adapter: ProfileAdapter

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    private fun initRc() {
        adapter = ProfileAdapter { presenter.singOutUser(it) }
        binding.rcProfile.adapter = adapter
    }

    @ProvidePresenter
    fun providePresenter() = appComponent.provideProfilePresenter()

    override fun initializeBinding() = FragmentProfileBinding.inflate(layoutInflater)

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
        requireActivity().finish()
        requireActivity().startActivity(Intent(requireContext(),MainActivity::class.java))
    }

    override fun navigateToProfileInformation() {
        navigateTo(ProfileFragmentDirections.actionProfileFragmentToProfilePersonFragment())
    }
}