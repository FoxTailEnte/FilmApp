package com.example.cinematicapp.presentation.ui.autorization.registration.number

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentRegistrationNumberBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
import com.example.cinematicapp.repository.utils.ViewUtils.validatePhone
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class RegistrationNumberFragment :BaseFragment<FragmentRegistrationNumberBinding>(), RegistrationNumberView {

    @InjectPresenter
    lateinit var presenter: RegistrationNumberPresenter

    private fun validateNumber() = with(binding) {
        val status = edPhone.validatePhone(edPhoneText.text.toString())
        if(status) {
            setLoadingState(true)
            hideKeyBoard()
            presenter.checkUserPhone(edPhoneText.text.toString())
        }
    }

    private fun hideKeyBoard() {
        requireActivity().setKeyboardVisibility(false)
    }

    private fun setLoadingState(state: Boolean) = with(binding) {
        if (state) {
            btSendCode.isEnabled = false
            tvSendCode.visibility = View.GONE
            pbSendCode.visibility = View.VISIBLE
        } else {
            btSendCode.isEnabled = true
            tvSendCode.visibility = View.VISIBLE
            pbSendCode.visibility = View.GONE
        }
    }

    @ProvidePresenter
    fun provideRegistrationNumberPresenter() = appComponent.provideRegistrationNumberPresenter()

    override fun initializeBinding() = FragmentRegistrationNumberBinding.inflate(layoutInflater)

    override fun setupListener() = with(binding) {
        btSendCode.setOnClickListener {
            validateNumber()
        }
        btBackPress.setOnClickListener { navigateTo(R.id.logInFragment) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInputNumber()
    }

    override fun checkInputNumber() = with(binding.edPhoneText) {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Unit
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (text.toString().length == 1 && !text!!.startsWith("+")) {
                    removeTextChangedListener(this)
                    setText(getString(R.string.validate_number_start))
                    setSelection(text!!.lastIndex + 1)
                    addTextChangedListener(this)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                Unit
            }
        })
    }

    override fun sentCodeSuccess(phone: String, id: String) {
        navigateTo(
            RegistrationNumberFragmentDirections.actionRegistrationNumberFragmentToRegistrationCodeFragment(phone, id))
        setLoadingState(false)
    }

    override fun sentSms(phone: String) {
        presenter.sendSms(phone, requireActivity())
    }

    override fun verificationFailed() {
        Toast.makeText(requireContext(), getString(R.string.error_verify), Toast.LENGTH_SHORT).show()
        setLoadingState(false)
    }

    override fun userBeRegister() {
        Toast.makeText(requireContext(), getString(R.string.error_user_be_register), Toast.LENGTH_SHORT).show()
        setLoadingState(false)
    }
}