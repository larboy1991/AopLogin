package com.zhz.library.aop.login.demo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.zhz.library.aop.login.controller.LoginSDK

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_layout)

        findViewById<Button>(R.id.mLoginBtn).setOnClickListener {
            MyApplication.instance.loginStatus = true
            LoginSDK.loginSuccess()
            finish()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        LoginSDK.loginFailed()
    }

}