package com.example.cinematicapp.presentation.ui.autorization.forgotPassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinematicapp.databinding.FragmentForgotPasswordBinding
import com.example.cinematicapp.repository.utils.Extensions.navigateBack

class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btBackPress.setOnClickListener { navigateBack() }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ForgotPasswordFragment()
    }
}