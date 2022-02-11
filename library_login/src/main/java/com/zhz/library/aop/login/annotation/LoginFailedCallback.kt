package com.zhz.library.aop.login.annotation

/**
 * 登录失败时调用的方法，需要开发人员手动调用，比如在登陆页面退出的时候调用下
 * <p>
 * Date: 2022-02-11
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class LoginFailedCallback