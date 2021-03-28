package com.example.ab.transformers.base

import android.os.Bundle
import androidx.lifecycle.ViewModel

abstract class BaseViewModelActivity : BaseActivity() {

    abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModelObservers()
    }

    abstract fun initViewModelObservers()

}