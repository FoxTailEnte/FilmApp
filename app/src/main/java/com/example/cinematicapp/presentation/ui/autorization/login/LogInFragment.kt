package com.example.cinematicapp.presentation.ui.autorization.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentLogInBinding
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class LogInFragment : MvpAppCompatFragment(), LogInView {

    @InjectPresenter
    lateinit var presenter: LogInPresenter
    private lateinit var binding: FragmentLogInBinding
    private var checkUserSingIn: Boolean = false


    private fun checkUserAuth() {
        if (checkUserSingIn) navigateTo(R.id.bottom_navigation_graph)
        else setupUi()
    }

    private fun setupUi() = with(binding) {
        forgotPassword.setOnClickListener { navigateTo(R.id.forgotPasswordFragment) }
        btSignIn.setOnClickListener { navigateTo(R.id.bottom_navigation_graph) }
        tvRegistration.setOnClickListener { navigateTo(R.id.graph_registration) }
    }

    @ProvidePresenter
    fun provideLoginPresenter() = appComponent.provideLoginPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkUserAuth()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LogInFragment()
    }
}