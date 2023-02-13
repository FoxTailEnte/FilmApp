package com.example.cinematicapp.presentation.ui.registration.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentRegistrationPersonInfoBinding
import com.example.cinematicapp.repository.network.firebase.models.UserModel
import com.example.cinematicapp.repository.utils.Extensions.clearBackStack
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class RegistrationPersonInfoFragment : MvpAppCompatFragment(), RegistrationPersonInfoView {
    private val args: RegistrationPersonInfoFragmentArgs by navArgs()

    @InjectPresenter
    lateinit var presenter: RegistrationPersonInfoPresenter
    private lateinit var binding: FragmentRegistrationPersonInfoBinding

    private fun setupUi() = with(binding) {
        btBackPress.setOnClickListener { navigateTo(R.id.logInFragment) }
        btFinish.setOnClickListener {
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
        val name = edNameText.text.toString()
        val secondName = edSecondNameText.text.toString()
        val pass = edPassText.text.toString()
        val userModel = UserModel(name, secondName, pass)
        if (edPassText.text?.trim()!!.isNotEmpty() && edRepeatPassText.text?.trim()!!.isNotEmpty()) {
            if (edPassText.text.toString() != edRepeatPassText.text.toString()) {
                showPassErrorToast()
            } else {
                if (validateName() && validateSecondName() && validatePass() && validatePassRepeat()) {
                    presenter.addNewUser(userModel, args.phone)
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
        onBackPress()
    }

    override fun completeRegistration() {
        navigateTo(R.id.logInFragment)
        Toast.makeText(requireContext(), getString(R.string.registration_person_complete_toast), Toast.LENGTH_SHORT)
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationPersonInfoFragment()
    }
}