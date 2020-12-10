package com.e.e_commerce.ui.authorisation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.e.e_commerce.R
import com.e.e_commerce.base.BaseActivity
import com.e.e_commerce.interfaces.CommonInterface
import com.e.e_commerce.prefernce.AppPreferences.api_token
import com.e.e_commerce.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: BaseActivity(), View.OnClickListener, CommonInterface {
    override val layoutId: Int
        get() = R.layout.activity_login
    override val setToolbar: Boolean
        get() = false
    override val hideStatusBar: Boolean
        get() = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cirLoginButton.setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        when (p0) {
            cirLoginButton -> {

                 if (valid()) {
                     setUpLoginObserver()
                 }
            }
        }
    }


    private fun setUpLoginObserver() {
        val loginViewModel = LoginViewModel(this)
        loginViewModel.login(editTextEmail.text.toString().trim(),editTextPassWord.text.toString().trim())
            .observe(this, Observer {
            if (it.status) {
                api_token =it.data.accessToken
                startActivity(Intent(this,MainActivity::class.java))

            } else {
                Toast.makeText(this, it.error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onShowProgress() {
        showProgress()
    }

    override fun onFailure() {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()

    }

    override fun onDismissProgress() {
        dismissProgress()
    }

    private fun valid(): Boolean {
        var isValid = false
        when {
            editTextEmail.text.isEmpty() -> textInputEmail.error="Enter Valid Email"
            editTextPassWord.text.isEmpty() -> textInputPass.error="Enter Valid Password"
            else -> isValid=true
        }
        return isValid
    }
}