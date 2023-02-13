package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.pass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentForgotPassNewPassBinding
import com.example.cinematicapp.repository.utils.Extensions.clearBackStack
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ForgotPasswordNewPassFragment : MvpAppCompatFragment(), ForgotPasswordNewPassView {
    private lateinit var binding: FragmentForgotPassNewPassBinding
    private val args: ForgotPasswordNewPassFragmentArgs by navArgs()

    @InjectPresenter
    lateinit var presenter: ForgotPasswordNewPassPresenter

    private fun setupUi() = with(binding) {
        btFinish.setOnClickListener {
            validatePass()
            validateRepeatPass()
            finalValidate()
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

    private fun validateRepeatPass(): Boolean = with(binding) {
        if (edRepeatPassText.text.toString().trim().isEmpty()) {
            edRepeatPass.error = getString(R.string.error_validate_number)
            false
        } else {
            edRepeatPass.isErrorEnabled = false
            true
        }
    }

    private fun finalValidate() = with(binding) {
        val pass = edPassText.text.toString()
        if (edPassText.text?.trim()!!.isNotEmpty() && edRepeatPassText.text?.trim()!!.isNotEmpty()) {
            if (edPassText.text.toString() != edRepeatPassText.text.toString()) {
                showPassErrorToast()
            } else {
                if (validatePass() && validatePass()) {
                    presenter.addNewPass(args.phone, pass)
                }
            }
        }
    }

    private fun showPassErrorToast() {
        Toast.makeText(requireContext(), getString(R.string.error_pass_coincidence), Toast.LENGTH_SHORT).show()
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
    fun provideForgotPasswordNewPassPresenter() =
        CinematicApplication.appComponent.provideForgotPasswordNewPassPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPassNewPassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPress()
        setupUi()
    }

    override fun setUserPass() {
        navigateTo(R.id.logInFragment)
        Toast.makeText(requireContext(), getString(R.string.forgot_password_finish_add_new_pass), Toast.LENGTH_SHORT)
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ForgotPasswordNewPassFragment()
    }
}