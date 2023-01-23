package com.example.cinematicapp.presentation.ui.registration.person

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentRegistrationPersonInfoBinding
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class RegistrationPersonInfoFragment : MvpAppCompatFragment(), RegistrationPersonInfoView {

    @InjectPresenter
    lateinit var presenter: RegistrationPersonInfoPresenter
    private lateinit var binding: FragmentRegistrationPersonInfoBinding

    private fun setupUi() = with(binding) {
        btBackPress.setOnClickListener { navigateTo(R.id.logInFragment) }
        btFinish.root.setOnClickListener {
            validateName()
            validateSecondName()
            validatePass()
            validatePassRepeat()
            finalValidate()
        }
    }

    private fun validateName(): Boolean = with(binding) {
        if (edNameText.text.toString().trim().isEmpty()) {
            edName.error = getString(R.string.error_validate_number)
            false
        } else {
            edName.isErrorEnabled = false
            true
        }
    }

    private fun validateSecondName(): Boolean = with(binding) {
        if (edSecondNameText.text.toString().trim().isEmpty()) {
            edSecondName.error = getString(R.string.error_validate_number)
            false
        } else {
            edSecondName.isErrorEnabled = false
            true
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

    private fun validatePassRepeat(): Boolean = with(binding) {
        if (edRepeatPassText.text.toString().trim().isEmpty()) {
            edRepeatPass.error = getString(R.string.error_validate_number)
            false
        } else {
            edRepeatPass.isErrorEnabled = false
            true
        }
    }

    private fun finalValidate() = with(binding) {
        if(edPassText.text?.trim()!!.isNotEmpty() && edRepeatPassText.text?.trim()!!.isNotEmpty()) {
            if(edPassText.text?.trim() != edRepeatPassText.text?.trim()) showPassErrorToast()
            if (validateName() && validateSecondName() && validatePass() && validatePassRepeat())
                navigateTo(RegistrationPersonInfoFragmentDirections
                    .actionRegistrationPersoneInfoFragmentToBottomNavigationGraph())
        }
    }

    private fun showPassErrorToast(){
        Toast.makeText(requireContext(),getString(R.string.error_pass_coincidence), Toast.LENGTH_SHORT).show()
    }

    @ProvidePresenter
    fun provideRegistrationNumberPresenter() = appComponent.provideRegistrationNumberPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
     binding = FragmentRegistrationPersonInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationPersonInfoFragment()
    }
}