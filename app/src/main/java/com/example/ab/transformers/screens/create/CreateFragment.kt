package com.example.ab.transformers.screens.create

import com.example.ab.transformers.R
import com.example.ab.transformers.base.BaseFragment
import com.example.ab.transformers.base.BaseViewModelFragment
import com.example.ab.transformers.screens.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateFragment : BaseViewModelFragment() {

    override val viewModel: MainViewModel by viewModel()

    override fun getActivityLayout(): Int = R.layout.fragment_create

    override fun initViews() {

    }

    override fun initViewModelObservers() {

    }
}