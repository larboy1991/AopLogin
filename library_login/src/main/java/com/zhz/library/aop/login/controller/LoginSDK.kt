package com.zhz.library.aop.login.controller

import com.zhz.library.aop.login.annotation.LoginFailedCallback
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
     * 登录成功回调 用户登录成功后需要手动调用，因为这样aop才会触发事件继续往下走
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     */
    fun loginSuccess() {
        helper.loginSuccess()
    }

    /**
     * 登录失败 ,用户退出登录页面之后需要手动调用，因为aop切面需要该回调后，才会触发[LoginFailedCallback]注解里面的方法
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     */
    fun loginFailed() {
        helper.loginFailed()

    }

    /**
     * 更新用户的退出状态,清空用户信息，并根据根据用户的type，跳转对应页面
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-12
     */
    fun updateUserLogoutStatus(type: Int) {
        helper.updateUserLogoutStatus(type)
    }
}