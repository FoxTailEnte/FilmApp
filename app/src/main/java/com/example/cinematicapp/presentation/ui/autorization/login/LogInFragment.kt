package com.example.cinematicapp.presentation.ui.autorization.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentLogInBinding
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class LogInFragment : MvpAppCompatFragment(), LogInView {
    private var testNumber = "+79885199537"
    private var testPass = "222110"

    @InjectPresenter
    lateinit var presenter: LogInPresenter
    private lateinit var binding: FragmentLogInBinding
    private var checkUserSingIn: Boolean = false


    private fun setupUi() = with(binding) {
        forgotPassword.setOnClickListener { navigateTo(R.id.forgotPasswordFragment) }
        btSignIn.root.setOnClickListener {
            hideKeyBoard()
            validateNumber()
            validatePass()
            finalValidate()
        }
        tvRegistration.setOnClickListener { navigateTo(R.id.graph_registration) }
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
            if (edNumberText.text.toString().length != 12 || !edNumberText.text!!.startsWith("+7")) {
                edNumber.error = getString(R.string.error_validate_size_number)
                false
            } else {
                edNumber.isErrorEnabled = false
                true
            }
        }
    }

    private fun checkPersonUserData() = with(binding) {
        if (edNumberText.text.toString() == testNumber && edPassText.text.toString() == testPass) {
            navigateTo(R.id.bottom_navigation_graph)
        } else if (edNumberText.text.toString() == testNumber) {
            Toast.makeText(requireContext(), getString(R.string.error_unknown_user_pass), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), getString(R.string.error_unknown_user_data), Toast.LENGTH_LONG).show()
        }
    }

    private fun finalValidate() {
        if (validateNumber() && validatePass()) checkPersonUserData()
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
                    edNumberText.setText(getString(R.string.validate_number_start))
                    edNumberText.setSelection(edNumberText.text!!.lastIndex + 1)
                    edNumberText.addTextChangedListener(this)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun hideKeyBoard() {
        requireActivity().setKeyboardVisibility(false)
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
        checkInputNumber()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LogInFragment()
    }
}