package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.number

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentForgotPasswordBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Constants
import com.example.cinematicapp.repository.utils.Extensions.navigateBack
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordView,
        ForgotPasswordPresenter>(), ForgotPasswordView {


    @InjectPresenter
    lateinit var presenter: ForgotPasswordPresenter

    override fun initializeBinding() = FragmentForgotPasswordBinding.inflate(layoutInflater)

    override fun setupListener() = with(binding) {
        btBackPress.setOnClickListener { navigateBack() }
        btSendCode.setOnClickListener {
            validateNumber()
        }
    }

    override fun checkInputNumber() = with(binding.edPhoneText) {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (text.toString().length == 1 && !text!!.startsWith(Constants.Validate.PLUS)) {
                    removeTextChangedListener(this)
                    setText(getString(R.string.validate_number_start))
                    setSelection(text!!.lastIndex + 1)
                    addTextChangedListener(this)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun sentCodeSuccess(phone: String, id: String) {
        navigateTo(
            ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToForgotPasswordCodeFragment2(
                phone,
                id
            )
        )
        setLoadingState(false)
    }

    override fun sentSms(phone: String) {
        presenter.sentSms(phone, requireActivity())
    }

    override fun userNotRegister() {
        Toast.makeText(
            requireContext(),
            getString(R.string.error_user_not_register),
            Toast.LENGTH_SHORT
        ).show()
        setLoadingState(false)
    }

    override fun verificationFailed() {
        Toast.makeText(requireContext(), getString(R.string.error_verify), Toast.LENGTH_SHORT)
            .show()
        setLoadingState(false)
    }

    override fun setLoadingState(isLoading: Boolean) = with(binding) {
        if (isLoading) {
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
    fun provideRegistrationNumberPresenter() =
        CinematicApplication.appComponent.provideForgotPasswordPresenter()

    private fun validateNumber() = with(binding) {
        if (presenter.validateText(edPhone, edPhoneText.text.toString())) {
            hideKeyBoard()
            checkUserPhone()
        }
    }

    private fun checkUserPhone() {
        setLoadingState(true)
        presenter.checkUserPhone(binding.edPhoneText.text.toString())

    }

    private fun hideKeyBoard() {
        requireActivity().setKeyboardVisibility(false)
    }
}