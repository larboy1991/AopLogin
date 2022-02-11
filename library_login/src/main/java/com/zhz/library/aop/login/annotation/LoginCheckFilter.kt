package com.zhz.library.aop.login.annotation

/**
 * 登录状态校验
 * <p>
 * Date: 2021-11-24
 * Updater:
 * Update Time:
 * Update Comments:
 * @param type Int 当没有登录的时候需要跳转页面的类型，本来是可以不需要的，不过在某些场景下，如果没有登录则需要跳转到非登录页面
 * @param resultCode Int 响应码，用于处理成功或失败时的回调处理
 * @param proceedWhenLoginSuccess Boolean 登录成功后继续执行方法里面的代码
 * @param loginSuccessCallback Boolean 登录成功后会进入到新的方法里面(需要使用注解 @[LoginSuccessCallback])进行注解
 * @constructor
 *
 * Author: zhuanghongzhan
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class LoginCheckFilter(
    val type: Int = 0,
    val resultCode: Int = 0,
    val proceedWhenLoginSuccess: Boolean = true,
    val loginSuccessCallback: Boolean = false
)
