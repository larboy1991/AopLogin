package com.zhz.library.aop.login.interf

import android.content.Context

/**
 * 登录接口回调
 * <p>
 * Date: 2022-02-11
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
interface ILogin {

    /**
     * 登录事件接收，内部自行处理需要跳转的页面
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     * @param type Int 跳转类型
     */
    fun login(type: Int)

    /**
     * 判断是否登录
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     * @return Boolean
     */
    fun isLogin(): Boolean

    /**
     * 退出登录时候调用，用来清空登录的信息
     * <p>
     * Author: zhuanghongzhan
     * Date: 2022-02-11
     */
    fun clearLoginStatus()
}