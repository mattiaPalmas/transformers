package com.example.ab.transformers.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel

abstract class BaseViewModelFragment : BaseFragment() {

    abstract val viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout =  super.onCreateView(inflater, container, savedInstanceState)

        initViewModelObservers()
        return layout
    }

    abstract fun initViewModelObservers()
}