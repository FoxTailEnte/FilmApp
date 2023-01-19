package com.example.cinematicapp.presentation.ui.profile.person

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.databinding.FragmentPersonInfoBinding
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class ProfilePersonFragment : MvpAppCompatFragment(), ProfilePersonView {

    @InjectPresenter
    lateinit var presenter: ProfilePersonPresenter
    private lateinit var binding: FragmentPersonInfoBinding

    private fun setupUi() = with(binding) {
    }

    @ProvidePresenter
    fun provideRegistrationNumberPresenter() = appComponent.provideRegistrationNumberPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
     binding = FragmentPersonInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfilePersonFragment()
    }
}