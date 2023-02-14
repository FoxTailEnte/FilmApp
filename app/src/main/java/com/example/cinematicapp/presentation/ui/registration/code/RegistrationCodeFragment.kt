package com.example.cinematicapp.presentation.ui.registration.code

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentRegistrationCodeBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.clearBackStack
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class RegistrationCodeFragment : BaseFragment<FragmentRegistrationCodeBinding>(), RegistrationCodeView {
    private val args: RegistrationCodeFragmentArgs by navArgs()

    @InjectPresenter
    lateinit var presenter: RegistrationCodePresenter
    private lateinit var timer: CountDownTimer

    private fun validateCode() = with(binding) {
        if (edConfirmCodeText.text.toString().trim().isEmpty()) {
            edConfirmCode.error = getString(R.string.error_validate_number)
        } else {
            edConfirmCode.isErrorEnabled = false
            setLoadingState(true)
            presenter.enterCode(args.id, edConfirmCodeText.text.toString())
        }
    }

    override fun onBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(this as LifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateTo(R.id.logInFragment)
                clearBackStack()

            }
        })
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

    private fun setLoadingState(loading: Boolean) = with(binding) {
        if (loading) {
            btConfirmCode.isEnabled = false
            tvConfirmCode.isVisible = false
            pbConfirmCode.isVisible = true
        } else {
            btConfirmCode.isEnabled = true
            tvConfirmCode.isVisible = true
            pbConfirmCode.isVisible = false
        }
    }

    @ProvidePresenter
    fun provideRegistrationCodePresenter() = appComponent.provideRegistrationCodePresenter()

    override fun initializeBinding() = FragmentRegistrationCodeBinding.inflate(layoutInflater)

    override fun setupListener() = with(binding) {
        tvReSentCode.setOnClickListener { presenter.authUser(args.phone, requireActivity()) }
        btConfirmCode.setOnClickListener { validateCode() }
        btBackPress.setOnClickListener { navigateTo(R.id.registrationNumberFragment) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startCountDownTimer()
    }

    override fun onDetach() {
        timer.cancel()
        super.onDetach()
    }

    override fun confirmCodeSuccess() {
        navigateTo(RegistrationCodeFragmentDirections
                .actionRegistrationCodeFragmentToRegistrationPersoneInfoFragment(args.phone))
        setLoadingState(false)
    }

    override fun confirmCodeFailToast() {
        Toast.makeText(requireContext(), getString(R.string.error_confirm_code_fail), Toast.LENGTH_SHORT).show()
        setLoadingState(false)
    }

    override fun verificationFailed() {
        Toast.makeText(requireContext(), getString(R.string.error_verify), Toast.LENGTH_SHORT).show()
    }

    override fun sentCodeSuccess(phone: String, id: String) {
        Toast.makeText(requireContext(), getString(R.string.registration_send_repeat_code), Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val MILLISECONDS_PER_SECOND: Long = 1000
        const val MILLISECONDS_PER_MINUTE: Long = 300000
    }
}