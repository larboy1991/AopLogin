package com.zhz.library.aop.login.aspect

import com.zhz.library.aop.login.annotation.LoginCheckFilter
import com.zhz.library.aop.login.annotation.LoginFailedCallback
import com.zhz.library.aop.login.annotation.LoginSuccessCallback
import com.zhz.library.aop.login.controller.LoginInnerHelper
import com.zhz.library.aop.login.interf.ILoginStatusCallback
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import kotlin.Exception

/**
 * 登录校验切面类
 * <p>
 * Date: 2021-11-24
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
@Aspect
class LoginCheckAspect {


    @Pointcut("execution(@com.zhz.library.aop.login.annotation.LoginCheckFilter * *(..))")
    fun pointcutLoginCheck() {
        //do nothing
    }

    @Around("pointcutLoginCheck()")
    fun aroundLoginCheckPoint(joinPoint: ProceedingJoinPoint) {
        val objectInfo = joinPoint.`this` ?: return
        val iLogin = LoginInnerHelper.iLogin ?: throw Exception("LoginSdk 没有初始化！")
        val signature = joinPoint.signature
        if (signature !is MethodSignature) {
            throw Exception("LoginCheckFilter 注解只能用于方法上！")
        }
        val loginCheckFilter: LoginCheckFilter =
            signature.method.getAnnotation(LoginCheckFilter::class.java) ?: throw Exception("没有找到LoginCheckFilter注解")
        //如果已经登录则直接进入方法里面去
        if (iLogin.isLogin()) {
            joinPoint.proceed()
        } else {
            //如果没有登录则跳转到对应操作页面去，并且创建一个回调，等登录成功后调用回调，让他重新进入到方法里面，可根据proceedWhenLoginSuccess进行控制进入或者不进入
            if (loginCheckFilter.proceedWhenLoginSuccess) {
                LoginInnerHelper.setLoginStatusCallback(object : ILoginStatusCallback {
                    override fun loginSuccess() {
                        if (iLogin.isLogin()) {
                            //进入到新的方法里面去
                            if (loginCheckFilter.loginSuccessCallback) {
                                invokeLoginSuccessCallback(objectInfo, loginCheckFilter.requestCode)
                            } else { //回到方法提里面去
                                joinPoint.proceed()
                            }

                        }
                        LoginInnerHelper.removeLoginStatusCallBack()
                    }

                    override fun loginFailed() {
                        invokeLoginFailedCallback(objectInfo,loginCheckFilter.requestCode)
                        LoginInnerHelper.removeLoginStatusCallBack()
                    }
                })
            }
            iLogin.login(loginCheckFilter.type)
        }
    }

    /**
     * 调用登录失败的回调 ，这边时进入到一个新的方法里面去，需要使用注解 @LoginFailedCallback
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     */
    private fun invokeLoginFailedCallback(any: Any, resultCode: Int) {
        val cls = any.javaClass
        val methods = cls.declaredMethods
        if (methods.isEmpty()) return
        for (method in methods) {
            val isHasAnnotation = method.isAnnotationPresent(LoginFailedCallback::class.java)
            if (isHasAnnotation) {
                method.isAccessible = true
                if (method.getAnnotation(LoginFailedCallback::class.java) != null) {
                    val types = method.parameterTypes
                    if (types.isNullOrEmpty()) {
                        try {
                            method.invoke(any)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        return
                    }

                    if (types.size == 1 && types.first().name == Int::class.java.name) {
                        try {
                            method.invoke(any, resultCode)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        return
                    }
                }
            }
        }
    }

    /**
     * 登录成功后进入到新的方法里面去 需要使用 @[LoginSuccessCallback]注解进行注解方法
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     * @param any Any
     */
    private fun invokeLoginSuccessCallback(any: Any, resultCode: Int) {
        val cls = any.javaClass
        val methods = cls.declaredMethods
        if (methods.isEmpty()) return
        for (method in methods) {
            val isHasAnnotation = method.isAnnotationPresent(LoginSuccessCallback::class.java)
            if (isHasAnnotation) {
                method.isAccessible = true
                if (method.getAnnotation(LoginSuccessCallback::class.java) != null) {
                    val types = method.parameterTypes
                    if (types.isNullOrEmpty()) {
                        try {
                            method.invoke(any)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        return
                    }

                    if (types.size == 1 && types.first().name == Int::class.java.name) {
                        try {
                            method.invoke(any, resultCode)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        return
                    }
                }
            }
        }
    }

}