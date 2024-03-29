package com.example.cinematicapp.presentation.ui.profile.pass.code

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentNewPassCodeBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.getColor
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class NewPassCodeFragment : BaseFragment<FragmentNewPassCodeBinding>(), NewPassCodeCodeView {

    @InjectPresenter
    lateinit var presenter: NewPassCodePresenter
    private lateinit var timer: CountDownTimer
    private val args: NewPassCodeFragmentArgs by navArgs()
    private val phone = presenter.getNumber()

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
            tvConfirmCode.isVisible = false
            pbConfirmCode.isVisible = true
        } else {
            btConfirmCode.isEnabled = true
            tvConfirmCode.isVisible = true
            pbConfirmCode.isVisible = false
        }
    }

    private fun getNumber() {
        presenter.authUser(phone,requireActivity())
    }

    @SuppressLint("SetTextI18n")
    private fun startCountDownTimer() = with(binding.tvReSentCode) {
        isEnabled = false
        var currentTime = MILLISECONDS_PER_MINUTE
        timer = object : CountDownTimer(currentTime, MILLISECONDS_PER_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                setTextColor(R.color.error_edText.getColor(context))
                currentTime = millisUntilFinished
                text = getString(
                    R.string.registration_restore_sms_code,
                    millisUntilFinished / 1000
                )
            }

            override fun onFinish() {
                text = getString(R.string.registration_repeat_code)
                isEnabled = true
                setTextColor(R.color.white.getColor(context))
            }

        }.start()
    }

    @ProvidePresenter
    fun provideNewPassCodePresenter() = CinematicApplication.appComponent.provideNewPassCodePresenter()

    override fun initializeBinding() = FragmentNewPassCodeBinding.inflate(layoutInflater)

    override fun setupListener() = with(binding) {
        tvReSentCode.setOnClickListener { getNumber() }
        btConfirmCode.setOnClickListener { validateCode() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startCountDownTimer()
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    override fun confirmCodeSuccess() {
        navigateTo(NewPassCodeFragmentDirections.actionNewPassCodeFragmentToNewPassNewPassFragment(phone))
        setLoadingState(false)
    }

    override fun verificationFailed() {
        Toast.makeText(requireContext(), getString(R.string.error_verify), Toast.LENGTH_SHORT).show()
    }

    override fun sentCodeSuccess() {
        Toast.makeText(requireContext(), getString(R.string.registration_send_repeat_code), Toast.LENGTH_SHORT).show()
    }

    override fun sentCode(option: PhoneAuthOptions.Builder) {
        PhoneAuthProvider.verifyPhoneNumber(option.setActivity(requireActivity()).build())
    }

    override fun confirmCodeFailToast() {
        Toast.makeText(requireContext(), getString(R.string.error_confirm_code_fail), Toast.LENGTH_SHORT).show()
        setLoadingState(false)
    }

    companion object {
        const val MILLISECONDS_PER_SECOND: Long = 1000
        const val MILLISECONDS_PER_MINUTE: Long = 300000
    }
}