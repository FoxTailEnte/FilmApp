package com.example.cinematicapp.presentation.ui.autorization.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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


    private fun setupUi() = with(binding) {
        forgotPassword.setOnClickListener { navigateTo(R.id.forgotPasswordFragment) }
        btSignIn.setOnClickListener {
            validateNumber()
            validatePass()
            finalValidate()
        }
        tvRegistration.setOnClickListener { navigateTo(R.id.graph_registration) }
        checkInputNumber()
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

    private fun validateNumber(): Boolean = with(binding) {
        if (edNumberText.text.toString().trim().isEmpty()) {
            edNumber.error = getString(R.string.error_validate_number)
            false
        } else {
            if (edNumberText.text.toString().length != 12) {
                edNumber.error = getString(R.string.error_validate_size_number)
                false
            } else {
                edNumber.isErrorEnabled = false
                true
            }
        }
    }

    private fun finalValidate() {
        if (validateNumber() && validatePass()) navigateTo(R.id.bottom_navigation_graph)
    }

    private fun checkUserAuth() {
        if (checkUserSingIn) navigateTo(R.id.bottom_navigation_graph)
        else setupUi()
    }

    private fun checkInputNumber() = with(binding) {
        edNumberText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edNumberText.text.toString().length == 1 && !edNumberText.text!!.startsWith("+")) {
                    edNumberText.removeTextChangedListener(this)
                    edNumberText.setText("+7$s")
                    edNumberText.setSelection(edNumberText.text!!.lastIndex + 1)
                    edNumberText.addTextChangedListener(this)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
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