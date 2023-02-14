package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.number

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentForgotPasswordBinding
import com.example.cinematicapp.repository.utils.Constants
import com.example.cinematicapp.repository.utils.Extensions.navigateBack
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ForgotPasswordFragment : MvpAppCompatFragment(), ForgotPasswordView {

    @InjectPresenter
    lateinit var presenter: ForgotPasswordPresenter
    private lateinit var binding: FragmentForgotPasswordBinding

    private fun setupUi() = with(binding) {
        btBackPress.setOnClickListener { navigateBack() }
        btSendCode.setOnClickListener {
            validateNumber()
        }
    }

    private fun validateNumber() = with(binding) {
        if (edPhoneText.text.toString().trim().isEmpty()) {
            edPhone.error = getString(R.string.error_validate_number)
        } else {
            if (edPhoneText.text.toString().length != 12 || !edPhoneText.text!!.startsWith(Constants.VALIDATE_NUMBER)) {
                edPhone.error = getString(R.string.error_validate_size_number)
            } else {
                edPhone.isErrorEnabled = false
                setLoadingState(true)
                hideKeyBoard()
                presenter.checkUserPhone(edPhoneText.text.toString(),requireActivity())
            }
        }
    }

    private fun setLoadingState(loading: Boolean) = with(binding) {
        if (loading) {
            btSendCode.isEnabled = false
            tvSendCode.visibility = View.GONE
            pbSendCode.visibility = View.VISIBLE
        } else {
            btSendCode.isEnabled = true
            tvSendCode.visibility = View.VISIBLE
            pbSendCode.visibility = View.GONE
        }
    }

    private fun hideKeyBoard() {
        requireActivity().setKeyboardVisibility(false)
    }

    private fun checkInputNumber() = with(binding.edPhoneText) {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
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
            }

        })
    }

    @ProvidePresenter
    fun provideRegistrationNumberPresenter() = CinematicApplication.appComponent.provideForgotPasswordPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        checkInputNumber()
    }

    override fun userNotRegister() {
        Toast.makeText(requireContext(), getString(R.string.error_user_not_register), Toast.LENGTH_SHORT).show()
        setLoadingState(false)
    }

    override fun verificationFailed() {
        Toast.makeText(requireContext(), getString(R.string.error_verify), Toast.LENGTH_SHORT).show()
        setLoadingState(false)
    }

    override fun sentCodeSuccess(phone: String, id: String) {
        navigateTo(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToForgotPasswordCodeFragment(phone, id))
        setLoadingState(false)
    }
}