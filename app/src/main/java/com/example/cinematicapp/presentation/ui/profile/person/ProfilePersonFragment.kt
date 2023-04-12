package com.example.cinematicapp.presentation.ui.profile.person

import android.widget.Toast
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentPersonInfoBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class ProfilePersonFragment : BaseFragment<FragmentPersonInfoBinding, ProfilePersonView, ProfilePersonPresenter>(), ProfilePersonView {

    @InjectPresenter
    lateinit var presenter: ProfilePersonPresenter

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

    private fun finalValidate() = with(binding) {
        if (edSecondNameText.text?.trim()!!.isNotEmpty() && edSecondNameText.text?.trim()!!.isNotEmpty()) {
                if (validateName() && validateSecondName()) {
                    presenter.getUserPhone()
            }
        }
    }

    @ProvidePresenter
    fun provideProfilePersonPresenter() = appComponent.provideProfilePersonPresenter()

    override fun initializeBinding() = FragmentPersonInfoBinding.inflate(layoutInflater)

    override fun setupListener() = with(binding) {
        btFinish.setOnClickListener {
            validateName()
            validateSecondName()
            finalValidate()
        }
    }

    override fun addNewUser(phone: String) = with(binding) {
        val name = edNameText.text.toString()
        val secondName = edSecondNameText.text.toString()
        presenter.addNewUserName(phone, name, secondName)
    }

    override fun addNewUserSuccess() {
        Toast.makeText(requireContext(), getString(R.string.profile_add_new_name_success), Toast.LENGTH_LONG).show()
    }

    override fun setLoadingState(isLoading: Boolean) {
        TODO("Not yet implemented")
    }
}