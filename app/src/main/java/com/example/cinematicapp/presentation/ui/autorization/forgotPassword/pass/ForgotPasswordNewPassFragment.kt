package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.pass

import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentForgotPassNewPassBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.navigateBack
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ForgotPasswordNewPassFragment : BaseFragment<FragmentForgotPassNewPassBinding,
        ForgotPasswordNewPassView, ForgotPasswordNewPassPresenter>(), ForgotPasswordNewPassView {
    private val args: ForgotPasswordNewPassFragmentArgs by navArgs()


    @InjectPresenter
    lateinit var presenter: ForgotPasswordNewPassPresenter

    override fun initializeBinding() = FragmentForgotPassNewPassBinding.inflate(layoutInflater)

    override fun setupListener() {
        with(binding) {
            btFinish.setOnClickListener {
                validate()
            }
        }
    }

    override fun setUserPass() {
        navigateBack()
        Toast.makeText(requireContext(), getString(R.string.forgot_password_finish_add_new_pass), Toast.LENGTH_SHORT).show()
    }

    override fun setLoadingState(isLoading: Boolean) {}

    @ProvidePresenter
    fun provideForgotPasswordNewPassPresenter() =
        CinematicApplication.appComponent.provideForgotPasswordNewPassPresenter()

    private fun validate() = with(binding) {
        val passValidate = presenter.validateText(edPass, edPassText.text.toString())
        val repeatValidate = presenter.validateText(edRepeatPass, edRepeatPassText.text.toString())
        if (edPassText.text?.trim()!!.isNotEmpty() &&
            edRepeatPassText.text?.trim()!!.isNotEmpty()) {
            if (edPassText.text.toString() != edRepeatPassText.text.toString()) {
                showPassErrorToast()
            } else {
                if (passValidate && repeatValidate) {
                    presenter.addNewPass(args.phone, edPassText.text.toString())
                }
            }
        }
    }

    private fun showPassErrorToast() {
        Toast.makeText(requireContext(), getString(R.string.error_pass_coincidence), Toast.LENGTH_SHORT).show()
    }
}