package com.example.cinematicapp.presentation.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import moxy.MvpAppCompatFragment

abstract class BaseFragment<
        out VB : ViewBinding,
        V: BaseView,
        out P:BasePresenter<V>
    >: MvpAppCompatFragment(), BaseView {

    protected val binding: VB
    get() = _binding!!
    private var _binding: VB? = null

    open fun setupUi() = Unit
    open fun setupListener() = Unit
    open fun checkUserAuth() = Unit
    open fun checkInputNumber() = Unit
    open fun onBackPress() = Unit

    abstract fun initializeBinding(): VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        checkInputNumber()
        checkUserAuth()
        setupListener()
        onBackPress()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = initializeBinding()
        return binding.root
    }
}