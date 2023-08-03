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
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class RegistrationPersonInfoFragment : BaseFragment<FragmentRegistrationPersonInfoBinding,
        RegistrationPersonInfoView, RegistrationPersonInfoPresenter>(), RegistrationPersonInfoView {
    private val args: RegistrationPersonInfoFragmentArgs by navArgs()


    @InjectPresenter
    lateinit var presenter: RegistrationPersonInfoPresenter

    override fun initializeBinding() = FragmentRegistrationPersonInfoBinding.inflate(layoutInflater)

    override fun setupListener() = with(binding) {
        btBackPress.setOnClickListener { navigateTo(R.id.logInFragment) }
        btFinish.setOnClickListener {
            validate()
            hideKeyBoard()
        }
    }

    override fun completeRegistration() {
        navigateBack()
        Toast.makeText(requireContext(), getString(R.string.registration_person_complete_toast), Toast.LENGTH_SHORT)
            .show()
    }

    override fun setLoadingState(isLoading: Boolean) {}

    @ProvidePresenter
    fun provideRegistrationPersonInfoPresenter() = appComponent.provideRegistrationPersonInfoPresenter()

    private fun validate() = with(binding) {
        val nameValidate = presenter.validateText(edName, edNameText.text.toString())
        val secondValidate = presenter.validateText(edSecondName, edSecondNameText.text.toString())
        val passValidate = presenter.validateText(edPass, edPassText.text.toString())
        val repeatValidate = presenter.validateText(edRepeatPass, edRepeatPassText.text.toString())
        if (edPassText.text?.trim()!!.isNotEmpty() &&
            edRepeatPassText.text?.trim()!!.isNotEmpty()) {
            if (edPassText.text.toString() != edRepeatPassText.text.toString()) {
                showPassErrorToast()
            } else {
                if (nameValidate && secondValidate && passValidate && repeatValidate) {
                    addNewUser()
                }
            }
        }
    }

    private fun addNewUser() = with(binding) {
        val userModel = UserModel(
            edNameText.text.toString(),
            edSecondNameText.text.toString(),
            edPassText.text.toString()
        )
        presenter.addNewUser(userModel, args.phone)
    }

    private fun hideKeyBoard() {
        requireActivity().setKeyboardVisibility(false)
    }

    private fun showPassErrorToast() {
        Toast.makeText(requireContext(), getString(R.string.error_pass_coincidence), Toast.LENGTH_SHORT).show()
    }
}