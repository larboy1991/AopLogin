## 一、集成配置

[![](https://jitpack.io/v/larboy1991/AopLogin.svg)](https://jitpack.io/#larboy1991/AopLogin)

#### 1、在项目的build.gradle里面加入这个

```gradle
buildscript {
    repositories {
       	...
        maven { url "https://jitpack.io" }
    }
    dependencies {
 		...
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.10'
    }
}

allprojects {
    repositories {
    	...
        maven { url "https://jitpack.io" }
    }
}

```
#### 2、module下的build.gradle添加以下配置
```gradle
plugins {
	...
	id 'com.hujiang.android-aspectjx'
}
//在项目的build.gradle里面需要添加扫描的目录
aspectjx {
	include 'com.zhz.library.aop.login'
	include '自己的项目包名'
}
...
dependencies {
	implementation 'com.github.larboy1991:AopLogin:$verison'
}
```

## 二、使用举例
#### 1、初始化
```kotlin
LoginSDK.init(object : ILogin {
            override fun login(type: Int) {
                //这边根据type进行跳转登录页面，或者其他业务页面，默认type为0,
                val intent = Intent(this@MyApplication, LoginActivity::class.java)
                intent.flags += Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }

            override fun isLogin(): Boolean {
            	//这边是判断用户是否登录，这边以用户token进行判断，如果用户的token为空则表示没有登录
                return loginStatus
            }

            override fun clearLoginStatus() {
                //这边为清空登录状态，也就是用户手动清空token、用户信息等
            }

        })

```


> 上面判断是否登录是本地简单的判断，如果token不为空，但是token失效了。那么此时请求接口，接口应返回401或其他约定好的状态码。
那么可根据这个状态码，调用```LoginSDK.updateUserLogoutStatus(type)```,使其进入到登录页面，重新登录。

#### 2、简单使用

##### 1、LoginCheckFilter
在需要登录才能执行的方法上使用注解
```kotlin
@LoginCheckFilter
private fun checkLogin() {
	Toast.makeText(this, "User is already login", Toast.LENGTH_SHORT).show()
}
```

如果用户已经登录就会直接进入方法里面，如果用户没有登录则会调用```ILogin.login(type)```方法跳转到登录页面去。

> 注意，用户登录成功后需要手动调用```LoginSDK.loginSuccess()```来触发使其aop的process继续往下执行，重新进入到注解的方法里面去。

其中LoginCheckFilter有多个参数：

* type：Int 当没有登录的时候需要跳转页面的类型，本来是可以不需要的，不过在某些场景下，如果没有登录则需要跳转到非登录页面
* requestCode：Int 请求码，用于登录成功或失败时回调方法内部区分
* proceedWhenLoginSuccess： Boolean 登录成功后继续执行方法里面的代码，默认为true，并且前提是用户登录成功后手动调用了```LoginSDK.loginSuccess()```
* loginSuccessCallback : Boolean 登录成功后会进入到新的方法里面(方法需要使用注解```LoginSuccessCallback```)  
<br>

##### 2、LoginFailedCallback
如果需要登录失败的回调，则在对应的方法上面添加，这边可以是无参的，也可以是有参的,参数必须是int。
```kotlin
@LoginFailedCallback
private fun loginFailed() {
	Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show()
}
```
或
```kotlin
@LoginFailedCallback
private fun loginFailed(requestCode: Int) {
	Toast.makeText(this, "login failed , requestCode: $requestCode", Toast.LENGTH_SHORT).show()
}
```
这个是为了方便区分是哪个方法aop进入到登陆页面后登录失败,对应```LoginCheckFilter(requestCode=111)```

##### 3、LoginSuccessCallback
如果需要登录成功后执行新的方法，则可以使用该注解,需要配合```LoginCheckFilter(loginSuccessCallback = true,requestCode=111)```
```kotlin
@LoginSuccessCallback
private fun loginSuccess(resultCode: Int) {
	Toast.makeText(this, "User is login success", Toast.LENGTH_SHORT).show()
}
```