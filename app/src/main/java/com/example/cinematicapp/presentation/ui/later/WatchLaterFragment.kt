package com.example.cinematicapp.presentation.ui.later

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinematicapp.databinding.FragmentWatchLaterBinding


class WatchLaterFragment : Fragment() {
    private lateinit var binding: FragmentWatchLaterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
     binding = FragmentWatchLaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = WatchLaterFragment()
    }
}