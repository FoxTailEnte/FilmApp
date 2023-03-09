package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.pass

import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentForgotPassNewPassBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.presentation.ui.autorization.forgotPassword.number.ForgotPasswordView
import com.example.cinematicapp.repository.utils.Extensions.navigateBack
import com.example.cinematicapp.repository.utils.ViewUtils.validatePass
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ForgotPasswordNewPassFragment : BaseFragment<FragmentForgotPassNewPassBinding, ForgotPasswordNewPassView, ForgotPasswordNewPassPresenter>(), ForgotPasswordNewPassView {
    private val args: ForgotPasswordNewPassFragmentArgs by navArgs()

    @InjectPresenter
    lateinit var presenter: ForgotPasswordNewPassPresenter

    private fun validatePass(): Boolean = with(binding) {
        edPass.validatePass(edPassText.text.toString())
    }

    private fun validateRepeatPass(): Boolean = with(binding) {
        edRepeatPass.validatePass(edRepeatPassText.text.toString())
    }

    private fun finalValidate() = with(binding) {
        if (edPassText.text?.trim()!!.isNotEmpty() && edRepeatPassText.text?.trim()!!.isNotEmpty()) {
            if (edPassText.text.toString() != edRepeatPassText.text.toString()) {
                showPassErrorToast()
            } else {
                if (validatePass() && validatePass()) {
                    presenter.addNewPass(args.phone, edPassText.text.toString())
                }
            }
        }
    }

    private fun showPassErrorToast() {
        Toast.makeText(requireContext(), getString(R.string.error_pass_coincidence), Toast.LENGTH_SHORT).show()
    }

    @ProvidePresenter
    fun provideForgotPasswordNewPassPresenter() =
        CinematicApplication.appComponent.provideForgotPasswordNewPassPresenter()

    override fun initializeBinding() = FragmentForgotPassNewPassBinding.inflate(layoutInflater)

    override fun setupListener() {
        with(binding) {
            btFinish.setOnClickListener {
                validatePass()
                validateRepeatPass()
                finalValidate()
            }
        }
    }

    override fun setUserPass() {
        navigateBack()
        Toast.makeText(requireContext(), getString(R.string.forgot_password_finish_add_new_pass), Toast.LENGTH_SHORT).show()
    }
}