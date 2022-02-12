package com.zhz.library.aop.login.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.zhz.library.aop.login.annotation.LoginCheckFilter
import com.zhz.library.aop.login.annotation.LoginFailedCallback
import com.zhz.library.aop.login.annotation.LoginSuccessCallback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.mTestBtn).setOnClickListener {
            checkLogin()
        }
        findViewById<Button>(R.id.mStartLogoutActivity).setOnClickListener {
            MyApplication.instance.loginStatus = false
            Toast.makeText(this, "退出登录成功", Toast.LENGTH_SHORT).show()
        }
    }

    @LoginCheckFilter(requestCode = 11101)
    private fun checkLogin() {
        Log.d("zhuanghz", "checkLogin: ~~~~~~~~~~")
        Toast.makeText(this, "获取用户信息成功", Toast.LENGTH_SHORT).show()
    }

    /**
     * 登录失败时调用
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     */
    @LoginFailedCallback
    private fun loginFailed(/*resultCode: Int*/) {
        Log.d("zhuanghz", "loginFailed: 登录失败拉   ")
    }

    /**
     * 登录成功的回调，需要LoginCheckFilter设置参数loginSuccessCallback为true时才会进入
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     * @param resultCode Int
     */
    @LoginSuccessCallback
    private fun loginSuccess(resultCode: Int) {
        Log.d("zhuanghz", "loginSuccess: 登录成功拉: $resultCode")
        Toast.makeText(this, "调用新接口获取用户信息成功", Toast.LENGTH_SHORT).show()
    }


}