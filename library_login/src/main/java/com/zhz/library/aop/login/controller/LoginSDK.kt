package com.zhz.library.aop.login.controller

import android.content.Context
import com.zhz.library.aop.login.interf.ILogin

/**
 * 登录sdk实现类
 * <p>
 * Date: 2022-02-11
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
object LoginSDK {

    private val helper: LoginInnerHelper = LoginInnerHelper

    /**
     * 初始化
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     * @param iLogin ILogin
     */
    fun init(iLogin: ILogin) {
        helper.iLogin = iLogin
    }

    /**
     * 是否登录
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     * @return Boolean
     */
    fun isLogin(): Boolean = helper.isLogin()

    /**
     * 登录成功回调
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     */
    fun loginSuccess() {
        helper.loginSuccess()
    }

    /**
     * 登录失败
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     */
    fun loginFailed() {
        helper.loginFailed()
    }

}