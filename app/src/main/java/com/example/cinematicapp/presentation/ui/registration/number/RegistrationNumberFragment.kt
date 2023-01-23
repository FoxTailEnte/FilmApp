package com.example.cinematicapp.presentation.ui.registration.number

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentRegistrationNumberBinding
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
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
        btSendCode.root.setOnClickListener {
            validateNumber()
        }
        btBackPress.setOnClickListener { navigateTo(R.id.logInFragment) }
    }

    private fun setupView() = with(binding) {
        btSendCode.textView16.text = getString(R.string.send_code)
    }

    private fun setLoadingState(loading: Boolean) = with(binding) {
        if(loading){
            btSendCode.root.isEnabled = false
            btSendCode.textView16.visibility = View.GONE
            btSendCode.progressBar.visibility = View.VISIBLE
        } else {
            btSendCode.textView16.visibility = View.VISIBLE
            btSendCode.progressBar.visibility = View.GONE
        }
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

    private fun validateNumber() = with(binding) {
        if (edPhoneText.text.toString().trim().isEmpty()) {
            edPhone.error = getString(R.string.error_validate_number)
        } else {
            if (edPhoneText.text.toString().length != 12 || !edPhoneText.text!!.startsWith("+7")) {
                edPhone.error = getString(R.string.error_validate_size_number)
            } else {
                edPhone.isErrorEnabled = false
                setLoadingState(true)
                hideKeyBoard()
                presenter.authUser(edPhoneText.text.toString())
            }
        }
    }

    private fun hideKeyBoard() {
        requireActivity().setKeyboardVisibility(false)
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
        setupView()
        checkInputNumber()
    }

    override fun sentCode(option: PhoneAuthOptions.Builder) {
        PhoneAuthProvider.verifyPhoneNumber(option.setActivity(requireActivity()).build())
    }

    override fun sentCodeSuccess(phone: String, id: String) {
        navigateTo(RegistrationNumberFragmentDirections.actionRegistrationNumberFragmentToRegistrationCodeFragment(phone,id))
        setLoadingState(false)
    }

    override fun verificationFailed() {
        Toast.makeText(requireContext(), getString(R.string.error_verify), Toast.LENGTH_SHORT).show()
        setLoadingState(false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationNumberFragment()
    }
}