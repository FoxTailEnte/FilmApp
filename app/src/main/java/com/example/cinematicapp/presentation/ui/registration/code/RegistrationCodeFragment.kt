package com.example.cinematicapp.presentation.ui.registration.code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentRegistrationCodeBinding
import com.example.cinematicapp.repository.utils.Extensions.navigateBack
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class RegistrationCodeFragment : MvpAppCompatFragment(), RegistrationCodeView {
    private val args: RegistrationCodeFragmentArgs by navArgs()

    @InjectPresenter
    lateinit var presenter: RegistrationCodePresenter
    private lateinit var binding: FragmentRegistrationCodeBinding

    private fun setupUi() = with(binding) {
        btConfirmCode.setOnClickListener { presenter.enterCode(args.id,edConfirmCode.text.toString()) }
        btBackPress.setOnClickListener { navigateBack() }
    }

    @ProvidePresenter
    fun provideRegistrationCodePresenter() = appComponent.provideRegistrationCodePresenter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    override fun confirmCodeSuccess() {
        navigateTo(RegistrationCodeFragmentDirections.actionRegistrationCodeFragmentToRegistrationPersoneInfoFragment())
    }

    override fun confirmCodeFailToast() {
        Toast.makeText(requireContext(), getString(R.string.error_unknown), Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationCodeFragment()
    }
}