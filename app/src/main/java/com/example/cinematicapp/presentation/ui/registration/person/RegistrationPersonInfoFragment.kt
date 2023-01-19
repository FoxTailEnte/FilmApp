package com.example.cinematicapp.presentation.ui.registration.person

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.databinding.FragmentRegistrationPersonInfoBinding
import com.example.cinematicapp.repository.utils.Extensions.navigateBack
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class RegistrationPersonInfoFragment : MvpAppCompatFragment(), RegistrationPersonInfoView {

    @InjectPresenter
    lateinit var presenter: RegistrationPersonInfoPresenter
    private lateinit var binding: FragmentRegistrationPersonInfoBinding

    private fun setupUi() = with(binding) {
        btBackPress.setOnClickListener { navigateBack() }
        btFinish.setOnClickListener { navigateTo(RegistrationPersonInfoFragmentDirections.actionRegistrationPersoneInfoFragmentToBottomNavigationGraph()) }
    }

    @ProvidePresenter
    fun provideRegistrationNumberPresenter() = appComponent.provideRegistrationNumberPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
     binding = FragmentRegistrationPersonInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationPersonInfoFragment()
    }
}