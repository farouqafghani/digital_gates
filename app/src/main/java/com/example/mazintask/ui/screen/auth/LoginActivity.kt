package com.example.mazintask.ui.screen.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.mazintask.R
import com.example.mazintask.data.base.BaseActivity
import com.example.mazintask.data.manager.LoggedInUserManager
import com.example.mazintask.data.models.User
import com.example.mazintask.data.models.response.TokenResponse
import com.example.mazintask.data.network.web_services.WebServiceManager
import com.example.mazintask.ui.screen.main.MainActivity
import com.example.mazintask.util.Utils
import com.example.mazintask.util.ValidationHelper
import com.example.mazintask.util.setOnClickListener2
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class LoginActivity : BaseActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private val isValidInputs: Boolean
        get() {
            Utils().clearErrors(etUsername, etPassword)
            return ValidationHelper().validateField(etUsername) && ValidationHelper().validateField(
                etPassword
            )
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupBiometric()

        initListeners()
    }

    private fun initListeners() {
        btnLogin?.setOnClickListener2 {
            if (isValidInputs) {
                requestLogin(etUsername.text, etPassword.text)
            }
        }

        btnLoginBiometric?.setOnClickListener2 {
            biometricPrompt.authenticate(promptInfo)
        }
    }

    private fun requestLogin(username: String, password: String) {
        showLoadingDialog()
        WebServiceManager.getMyWebServices()
            .authorizeUser(
                username = username,
                password = password,
                clientId = "mobile",
                grantType = "password"
            )
            .enqueue(object : Callback<TokenResponse> {
                override fun onResponse(
                    call: Call<TokenResponse>,
                    response: Response<TokenResponse>
                ) {
                    hideLoadingDialog()
                    if (response.isSuccessful) {
                        val user = User(response.body())
                        LoggedInUserManager.user = user

                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    } else {
                        handleErrorResponse(response)
                    }
                }

                override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                    handleOnFailure(t)
                }
            })
    }

    private fun setupBiometric() {
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    requestLogin("testak@mailinator.com", "terokkar")
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Cancel")
            .build()
    }
}