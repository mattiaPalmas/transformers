package com.example.ab.transformers.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getActivityLayout())

        initViews()
    }

    @LayoutRes
    abstract fun getActivityLayout(): Int

    abstract fun initViews()
}