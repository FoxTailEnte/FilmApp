package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.code

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentForgotPassCodeBinding
import com.example.cinematicapp.presentation.ui.registration.code.RegistrationCodeFragmentArgs
import com.example.cinematicapp.repository.utils.Extensions.clearBackStack
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ForgotPasswordCodeFragment : MvpAppCompatFragment(), ForgotPasswordCodeView {

    @InjectPresenter
    lateinit var presenter: ForgotPasswordCodePresenter
    private lateinit var binding: FragmentForgotPassCodeBinding
    private lateinit var timer: CountDownTimer
    private val args: RegistrationCodeFragmentArgs by navArgs()

    private fun setupUi() = with(binding) {
        tvReSentCode.setOnClickListener { presenter.authUser(args.phone) }
        btConfirmCode.setOnClickListener { validateCode() }
    }

    private fun validateCode() = with(binding) {
        if (edConfirmCodeText.text.toString().trim().isEmpty()) {
            edConfirmCode.error = getString(R.string.error_validate_number)
        } else {
            edConfirmCode.isErrorEnabled = false
            setLoadingState(true)
            presenter.enterCode(args.id, edConfirmCodeText.text.toString())
        }
    }

    private fun setLoadingState(loading: Boolean) = with(binding) {
        if (loading) {
            btConfirmCode.isEnabled = false
            tvConfirmCode.visibility = View.GONE
            pbConfirmCode.visibility = View.VISIBLE
        } else {
            btConfirmCode.isEnabled = true
            tvConfirmCode.visibility = View.VISIBLE
            pbConfirmCode.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun startCountDownTimer() = with(binding.tvReSentCode) {
        isEnabled = false
        var currentTime = MILLISECONDS_PER_MINUTE
        timer = object : CountDownTimer(currentTime, MILLISECONDS_PER_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                setTextColor(resources.getColor(R.color.error_edText))
                currentTime = millisUntilFinished
                text = getString(
                    R.string.registration_restore_sms_code,
                    millisUntilFinished / 1000
                )
            }

            override fun onFinish() {
                text = getString(R.string.registration_repeat_code)
                isEnabled = true
                setTextColor(resources.getColor(R.color.white))
            }

        }.start()
    }

    private fun onBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateTo(R.id.logInFragment)
                clearBackStack()
            }
        })
    }

    @ProvidePresenter
    fun provideRegistrationCodePresenter() = CinematicApplication.appComponent.provideForgotPasswordCodePresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPassCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        startCountDownTimer()
        onBackPress()
    }

    override fun verificationFailed() {
        Toast.makeText(requireContext(), getString(R.string.error_verify), Toast.LENGTH_SHORT).show()
    }

    override fun sentCodeSuccess(phone: String, id: String) {
        Toast.makeText(requireContext(), getString(R.string.registration_send_repeat_code), Toast.LENGTH_SHORT).show()
    }

    override fun sentCode(option: PhoneAuthOptions.Builder) {
        PhoneAuthProvider.verifyPhoneNumber(option.setActivity(requireActivity()).build())
    }

    override fun confirmCodeSuccessToast() {
        navigateTo(
            ForgotPasswordCodeFragmentDirections
                .actionForgotPasswordCodeFragmentToForgotPasswordNewPassFragment(args.phone)
        )
        setLoadingState(false)
    }

    override fun confirmCodeFailToast() {
        Toast.makeText(requireContext(), getString(R.string.error_confirm_code_fail), Toast.LENGTH_SHORT).show()
        setLoadingState(false)
    }

    companion object {
        const val MILLISECONDS_PER_SECOND: Long = 1000
        const val MILLISECONDS_PER_MINUTE: Long = 300000

        @JvmStatic
        fun newInstance() = ForgotPasswordCodeFragment()
    }
}