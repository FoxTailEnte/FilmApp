package com.example.cinematicapp.presentation.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinematicapp.databinding.FragmentProfileBinding
import com.example.cinematicapp.presentation.adapters.profile.ProfileAdapter


class ProfileFragment : Fragment() {
    private lateinit var adapter: ProfileAdapter
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRc()
    }

    private fun initRc() {
        adapter = ProfileAdapter()
        binding.rcProfile.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}