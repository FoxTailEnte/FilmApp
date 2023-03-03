package com.example.cinematicapp.presentation.ui.autorization.registration.person

import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentRegistrationPersonInfoBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.network.firebase.models.UserModel
import com.example.cinematicapp.repository.utils.Extensions.navigateBack
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
import com.example.cinematicapp.repository.utils.ViewUtils.validate
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class RegistrationPersonInfoFragment : BaseFragment<FragmentRegistrationPersonInfoBinding>(),
    RegistrationPersonInfoView {
    private val args: RegistrationPersonInfoFragmentArgs by navArgs()

    @InjectPresenter
    lateinit var presenter: RegistrationPersonInfoPresenter

    private fun validateName(): Boolean = with(binding) {
        edName.validate(edNameText.text.toString())
    }

    private fun validateSecondName(): Boolean = with(binding) {
        edSecondName.validate(edSecondNameText.text.toString())
    }

    private fun validatePass(): Boolean = with(binding) {
        edPass.validate(edPassText.text.toString())
    }

    private fun validatePassRepeat(): Boolean = with(binding) {
        edRepeatPass.validate(edRepeatPassText.text.toString())
    }

    private fun finalValidate() = with(binding) {
        val userModel = UserModel(
            edNameText.text.toString(),
            edSecondNameText.text.toString(),
            edPassText.text.toString()
        )
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

    private fun hideKeyBoard() {
        requireActivity().setKeyboardVisibility(false)
    }

    private fun showPassErrorToast() {
        Toast.makeText(requireContext(), getString(R.string.error_pass_coincidence), Toast.LENGTH_SHORT).show()
    }

    @ProvidePresenter
    fun provideRegistrationPersonInfoPresenter() = appComponent.provideRegistrationPersonInfoPresenter()


    override fun initializeBinding() = FragmentRegistrationPersonInfoBinding.inflate(layoutInflater)

    override fun setupListener() = with(binding) {
        btBackPress.setOnClickListener { navigateTo(R.id.logInFragment) }
        btFinish.setOnClickListener {
            validateName()
            validateSecondName()
            validatePass()
            validatePassRepeat()
            finalValidate()
            hideKeyBoard()
        }
    }

    override fun completeRegistration() {
        navigateBack()
        Toast.makeText(requireContext(), getString(R.string.registration_person_complete_toast), Toast.LENGTH_SHORT)
            .show()
    }
}