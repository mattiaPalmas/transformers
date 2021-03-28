package com.example.ab.transformers.screens.splash

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ab.transformers.screens.main.MainActivity
import com.example.ab.transformers.R
import com.example.ab.transformers.base.BaseActivity


class SplashActivity : BaseActivity() {

    override fun getActivityLayout(): Int = R.layout.activity_splash

    override fun initViews() {

    }

    override fun onStart() {
        super.onStart()

        goToMainActivityAfterDelay()
    }

    private fun goToMainActivityAfterDelay() {
        Thread.sleep(3000)

        startActivity(Intent(this, MainActivity::class.java))
    }
}