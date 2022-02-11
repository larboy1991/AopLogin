package com.zhz.library.aop.login.demo

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import com.zhz.library.aop.login.controller.LoginSDK
import com.zhz.library.aop.login.interf.ILogin

class MyApplication : Application() {

    var loginStatus = false

    companion object {
        @JvmStatic
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initLoginSdk()
    }

    /**
     * 初始化登录sdk
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     */
    private fun initLoginSdk() {

        LoginSDK.init(object : ILogin {
            override fun login(type: Int) {
                Log.d("zhuanghz", "login: 需要跳转去登录页面 type: $type")
                val intent = Intent(this@MyApplication, LoginActivity::class.java)
                intent.flags += Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }

            override fun isLogin(): Boolean {
                return loginStatus
            }

            override fun clearLoginStatus() {
                Log.d("zhuanghz", "clearLoginStatus: 清除登录信息")
            }

        })


    }


}