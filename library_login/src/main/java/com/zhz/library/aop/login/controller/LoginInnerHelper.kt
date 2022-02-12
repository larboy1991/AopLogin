package com.zhz.library.aop.login.controller

import com.zhz.library.aop.login.interf.ILogin
import com.zhz.library.aop.login.interf.ILoginStatusCallback

/**
 * 登录aop内部工具
 * <p>
 * Date: 2022-02-11
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
internal object LoginInnerHelper {


    private var mLoginStatusCallback: ILoginStatusCallback? = null

    var iLogin: ILogin? = null

    /**
     * 判断是否已经登陆了
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     * @return Boolean
     */
    fun isLogin(): Boolean = iLogin?.isLogin() ?: false

    /**
     * 设置登陆状态回调
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     * @param loginStatusCallback ILoginStatusCallback
     */
    fun setLoginStatusCallback(loginStatusCallback: ILoginStatusCallback) {
        this.mLoginStatusCallback = loginStatusCallback
    }

    /**
     * 移除回调
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     */
    fun removeLoginStatusCallBack() {
        this.mLoginStatusCallback = null
    }

    /**
     * 登录成功
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     */
    fun loginSuccess() {
        mLoginStatusCallback?.loginSuccess()
    }

    /**
     * 登陆失败
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     */
    fun loginFailed() {
        mLoginStatusCallback?.loginFailed()
    }

    /**
     * 更新用户的退出状态,清空用户信息，并根据根据用户的type，跳转对应页面
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-12
     */
    fun updateUserLogoutStatus(type: Int) {
        iLogin?.clearLoginStatus()
        iLogin?.login(type)
    }
}