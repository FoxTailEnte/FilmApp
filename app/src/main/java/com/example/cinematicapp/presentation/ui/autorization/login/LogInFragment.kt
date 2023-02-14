package com.example.cinematicapp.presentation.ui.autorization.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentLogInBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Constants
import com.example.cinematicapp.repository.utils.Extensions.getBottomNavigation
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class LogInFragment : BaseFragment<FragmentLogInBinding>(), LogInView {

    @InjectPresenter
    lateinit var presenter: LogInPresenter

    private fun validateNumber(): Boolean = with(binding) {
        if (edNumberText.text.toString().trim().isEmpty()) {
            edNumber.error = getString(R.string.error_validate_number)
            false
        } else {
            if (edNumberText.text.toString().length != 12 || !edNumberText.text!!.startsWith(Constants.VALIDATE_NUMBER)) {
                edNumber.error = getString(R.string.error_validate_size_number)
                false
            } else {
                edNumber.isErrorEnabled = false
                true
            }
        }
    }

    private fun validatePass(): Boolean = with(binding) {
        if (edPassText.text.toString().trim().isEmpty()) {
            edPass.error = getString(R.string.error_validate_number)
            false
        } else {
            edPass.isErrorEnabled = false
            true
        }
    }

    private fun finalValidate() = with(binding) {
        val phone = edNumberText.text.toString()
        val pass = edPassText.text.toString()
        if (validateNumber() && validatePass()) {
            setLoadingState(true)
            presenter.authUser(phone, pass)
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
        getBottomNavigation()?.hideBottomNavigation(false)
    }

    override fun setupListener() {
        with (binding) {
            forgotPassword.setOnClickListener { navigateTo(LogInFragmentDirections.actionLogInFragmentToGraphForgotPass()) }
            btSignIn.setOnClickListener {
                hideKeyBoard()
                validateNumber()
                validatePass()
                finalValidate()
            }
            tvRegistration.setOnClickListener {
                navigateTo(
                    LogInFragmentDirections
                        .actionLogInFragmentToRegistrationNavigation()
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkUserAuth()
        checkInputNumber()
    }

    override fun checkUserAuth() {
        if (presenter.checkUserAuthStatus()) navigateTo(LogInFragmentDirections
                .actionLogInFragmentToBottomNavigationGraph())
    }

    override fun checkInputNumber() = with(binding) {
        edNumberText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
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
            }
        })
    }

    override fun userAuthComplete() {
        navigateTo(LogInFragmentDirections.actionLogInFragmentToBottomNavigationGraph())
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