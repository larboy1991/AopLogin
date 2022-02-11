package com.zhz.library.aop.login.annotation

/**
 * 登录成功进入到新的方法里面去，方法可使用参数为Int型的参数，来进行做区分
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
annotation class LoginSuccessCallback
