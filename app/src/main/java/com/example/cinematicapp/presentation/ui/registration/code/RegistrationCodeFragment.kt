package com.example.cinematicapp.presentation.ui.registration.code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.databinding.FragmentRegistrationCodeBinding
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class RegistrationCodeFragment : MvpAppCompatFragment(), RegistrationCodeView {

    @InjectPresenter
    lateinit var presenter: RegistrationCodePresenter
    private lateinit var binding: FragmentRegistrationCodeBinding

    private fun setupUi() = with(binding) {

    }

    @ProvidePresenter
    fun provideRegistrationCodePresenter() = appComponent.provideRegistrationCodePresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationCodeFragment()
    }
}