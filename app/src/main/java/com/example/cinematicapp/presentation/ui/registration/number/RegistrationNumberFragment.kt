package com.example.cinematicapp.presentation.ui.registration.number

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentRegistrationNumberBinding
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class RegistrationNumberFragment : MvpAppCompatFragment(), RegistrationNumberView {

    @InjectPresenter
    lateinit var presenter: RegistrationNumberPresenter
    private lateinit var binding: FragmentRegistrationNumberBinding

    private fun setupUi() = with(binding) {
        btSendCode.setOnClickListener { presenter.authUser(edPhone.text.toString()) }
        btBackPress.setOnClickListener { /*navigateBack()*/ }
    }

    @ProvidePresenter
    fun provideRegistrationNumberPresenter() = appComponent.provideRegistrationNumberPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
     binding = FragmentRegistrationNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    override fun sendCode(option: PhoneAuthOptions.Builder) {
        PhoneAuthProvider.verifyPhoneNumber(option.setActivity(requireActivity()).build())
    }

    override fun sendCodeSuccess(phone: String, id: String) {
       navigateTo(RegistrationNumberFragmentDirections.actionRegistrationNumberFragmentToRegistrationCodeFragment(phone,id))
    }

    override fun sendCodeFailToast() {
        Toast.makeText(requireContext(), getString(R.string.error_unknown), Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationNumberFragment()
    }
}