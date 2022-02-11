package com.zhz.library.aop.login.interf

/**
 * 登陆状态回调
 * <p>
 * Date: 2022-02-11
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
interface ILoginStatusCallback {

    /**
     * 登录成功
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     */
    fun loginSuccess()

    /**
     * 登陆失败
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     */
    fun loginFailed()

}