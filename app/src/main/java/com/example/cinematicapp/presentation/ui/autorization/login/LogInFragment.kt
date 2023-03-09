package com.example.cinematicapp.presentation.ui.autorization.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentLogInBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.presentation.ui.main.MainActivity
import com.example.cinematicapp.repository.utils.Constants
import com.example.cinematicapp.repository.utils.Extensions.getMainActivityView
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
import com.example.cinematicapp.repository.utils.ViewUtils.validatePass
import com.example.cinematicapp.repository.utils.ViewUtils.validatePhone
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class LogInFragment : BaseFragment<FragmentLogInBinding, LogInView, LogInPresenter>(), LogInView {

    @InjectPresenter
    lateinit var presenter: LogInPresenter

    private fun validateNumber(): Boolean = with(binding) {
        edNumber.validatePhone(edNumberText.text.toString())
    }

    private fun validatePass(): Boolean = with(binding) {
        edPass.validatePass(edPassText.text.toString())
    }

    private fun finalValidate() = with(binding) {
        if (validateNumber() && validatePass()) {
            setLoadingState(true)
            presenter.authUser(edNumberText.text.toString(), edPassText.text.toString())
            presenter.savePhone(edNumberText.text.toString())
        }
    }

    private fun setLoadingState(loading: Boolean) = with(binding) {
        if (loading) {
            btSignIn.isEnabled = false
            tvSignIn.isVisible = false
            pbSignIn.isVisible = true
        } else {
            btSignIn.isEnabled = true
            tvSignIn.isVisible = true
            pbSignIn.isVisible = false
        }
    }

    private fun hideKeyBoard() {
        requireActivity().setKeyboardVisibility(false)
    }

    @ProvidePresenter
    fun provideLoginPresenter() = appComponent.provideLoginPresenter()

    override fun initializeBinding() = FragmentLogInBinding.inflate(layoutInflater)

    override fun setupUi() {
        getMainActivityView()?.hideSearchMenu(false)
        getMainActivityView()?.hideBottomMenu(false)
    }

    override fun setupListener() {
        with (binding) {
            forgotPassword.setOnClickListener { navigateTo(LogInFragmentDirections.actionLogInFragmentToForgotPasswordFragment()) }
            btSignIn.setOnClickListener {
                hideKeyBoard()
                validateNumber()
                validatePass()
                finalValidate()
            }
            tvRegistration.setOnClickListener {
                navigateTo(
                    LogInFragmentDirections.actionLogInFragmentToRegistrationNumberFragment())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInputNumber()
    }

    override fun checkInputNumber() = with(binding) {
        edNumberText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Unit
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edNumberText.text.toString().length == 1 && !edNumberText.text!!.startsWith(Constants.PLUS)) {
                    edNumberText.removeTextChangedListener(this)
                    edNumberText.setText(getString(R.string.validate_number_start))
                    edNumberText.setSelection(edNumberText.text!!.lastIndex + 1)
                    edNumberText.addTextChangedListener(this)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                Unit
            }
        })
    }

    override fun userAuthComplete() {
        requireActivity().finish()
        requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))
        setLoadingState(false)
    }

    override fun onBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }

    override fun userNotFound() {
        Toast.makeText(requireContext(), getString(R.string.error_unknown_user_data), Toast.LENGTH_LONG).show()
        setLoadingState(false)
    }

    override fun userDataError() {
        Toast.makeText(requireContext(), getString(R.string.error_unknown_user_pass), Toast.LENGTH_LONG).show()
        setLoadingState(false)
    }
}