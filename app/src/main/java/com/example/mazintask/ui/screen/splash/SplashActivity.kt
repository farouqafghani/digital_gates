package com.example.mazintask.ui.screen.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.mazintask.ui.screen.main.MainActivity
import com.example.mazintask.R
import com.example.mazintask.data.manager.LoggedInUserManager
import com.example.mazintask.ui.screen.auth.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startProcess()
    }

    private fun startProcess() {
        Handler().postDelayed({
            if (LoggedInUserManager.isUserLoggedIn()) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }, 1000)
    }
}
