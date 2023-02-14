package com.example.cinematicapp.presentation.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.databinding.FragmentProfileBinding
import com.example.cinematicapp.presentation.adapters.profile.ProfileAdapter
import com.example.cinematicapp.presentation.ui.main.MainActivity
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class ProfileFragment: MvpAppCompatFragment(), ProfileView {
    private lateinit var adapter: ProfileAdapter
    private lateinit var binding: FragmentProfileBinding

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    private fun initRc() {
        adapter = ProfileAdapter { presenter.singOutUser(it) }
        binding.rcProfile.adapter = adapter
    }

    @ProvidePresenter
    fun providePresenter() = appComponent.provideProfilePresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRc()
    }

    override fun signOutComplete() {
        requireActivity().finish()
        requireActivity().startActivity(Intent(requireContext(),MainActivity::class.java))
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}