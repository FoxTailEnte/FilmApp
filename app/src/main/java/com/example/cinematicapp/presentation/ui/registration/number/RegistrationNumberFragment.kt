package com.example.cinematicapp.presentation.ui.registration.number

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentRegistrationNumberBinding
import com.example.cinematicapp.repository.utils.Constants
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class RegistrationNumberFragment : MvpAppCompatFragment(), RegistrationNumberView {

    @InjectPresenter
    lateinit var presenter: RegistrationNumberPresenter
    private lateinit var binding: FragmentRegistrationNumberBinding

    private fun setupUi() = with(binding) {
        btSendCode.setOnClickListener {
            validateNumber()
        }
        btBackPress.setOnClickListener { navigateTo(R.id.logInFragment) }
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
            if (edPhoneText.text.toString().length != 12 || !edPhoneText.text!!.startsWith(Constants.VALIDATE_NUMBER)) {
                edPhone.error = getString(R.string.error_validate_size_number)
            } else {
                edPhone.isErrorEnabled = false
                setLoadingState(true)
                hideKeyBoard()
                checkPhone(edPhoneText.text.toString())
            }
        }
    }

    private fun checkPhone(phone:String) {
        presenter.checkUserPhone(phone)
    }

    override fun sentSms(phone: String) {
        presenter.sendSms(phone,requireActivity())
    }

    private fun hideKeyBoard() {
        requireActivity().setKeyboardVisibility(false)
    }

    private fun onBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(this as LifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        })
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
        checkInputNumber()
        onBackPress()
    }

    override fun sentCodeSuccess(phone: String, id: String) {
        navigateTo(RegistrationNumberFragmentDirections
            .actionRegistrationNumberFragmentToRegistrationCodeFragment(phone, id))
        setLoadingState(false)
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