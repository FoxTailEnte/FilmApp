package com.example.cinematicapp.presentation.ui.profile.pass.newPass

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentNewPassNewPassBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.clearBackStack
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class NewPassNewPassFragment : BaseFragment<FragmentNewPassNewPassBinding>(), NewPassNewPassView {

    @InjectPresenter
    lateinit var presenter: NewPassNewPassPresenter
    private val args: NewPassNewPassFragmentArgs by navArgs()

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
                    presenter.addNewPass(args.phone,pass)
                }
            }
        }
    }

    private fun showPassErrorToast() {
        Toast.makeText(requireContext(), getString(R.string.error_pass_coincidence), Toast.LENGTH_SHORT).show()
    }

    @ProvidePresenter
    fun provideNewPassNewPassPresenter() = CinematicApplication.appComponent.provideNewPassNewPassPresenter()

    override fun initializeBinding() = FragmentNewPassNewPassBinding.inflate(layoutInflater)

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
        navigateTo(R.id.logInFragment)
        Toast.makeText(requireContext(), getString(R.string.forgot_password_finish_add_new_pass), Toast.LENGTH_SHORT)
            .show()
    }

    override fun onBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(this as LifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateTo(R.id.graph_profile)
                clearBackStack()
            }
        })
    }
}