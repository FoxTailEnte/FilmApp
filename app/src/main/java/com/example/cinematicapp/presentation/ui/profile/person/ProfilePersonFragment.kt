package com.example.cinematicapp.presentation.ui.profile.person

import android.widget.Toast
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentPersonInfoBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.navigateBack
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class ProfilePersonFragment :
    BaseFragment<FragmentPersonInfoBinding, ProfilePersonView, ProfilePersonPresenter>(),
    ProfilePersonView {

    @InjectPresenter
    lateinit var presenter: ProfilePersonPresenter

    private fun validate() = with(binding) {
        val validateName = presenter.validateText(edName, edNameText.text.toString())
        val validateSecondName =
            presenter.validateText(edSecondName, edSecondNameText.text.toString())
        if (edSecondNameText.text?.trim()!!.isNotEmpty() && edSecondNameText.text?.trim()!!
                .isNotEmpty()
        ) {
            if (validateName && validateSecondName) {
                presenter.getUserPhone()
            }
        }
    }

    @ProvidePresenter
    fun provideProfilePersonPresenter() = appComponent.provideProfilePersonPresenter()

    override fun initializeBinding() = FragmentPersonInfoBinding.inflate(layoutInflater)

    override fun setupListener() = with(binding) {
        btFinish.setOnClickListener {
            validate()
        }
        btBackPress.setOnClickListener {
            navigateBack()
        }
    }

    override fun addNewUser(phone: String) = with(binding) {
        val name = edNameText.text.toString()
        val secondName = edSecondNameText.text.toString()
        presenter.addNewUserName(phone, name, secondName)
    }

    override fun addNewUserSuccess() {
        Toast.makeText(
            requireContext(),
            getString(R.string.profile_add_new_name_success),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun setLoadingState(isLoading: Boolean) {}
}