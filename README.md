## 一、配置

[![](https://jitpack.io/v/larboy1991/AopLogin.svg)](https://jitpack.io/#larboy1991/AopLogin)

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
		classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.10'
	}

	dependencies {
			 implementation 'com.github.larboy1991:AopLogin:0.0.1'
	}



	plugins {
        ...
        id 'com.hujiang.android-aspectjx'
    }
    //在项目的build.gradle里面需要添加扫描的目录
    aspectjx {
        include 'com.zhz.library.aop.login'
        include '自己的项目包名'
    }

## 二、使用举例
    参考app里面的demo


