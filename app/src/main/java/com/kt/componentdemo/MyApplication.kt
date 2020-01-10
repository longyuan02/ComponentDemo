package com.kt.componentdemo

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.kt.componentdemo.annotation.ProxyUtils

class MyApplication : Application() {
    private val isDebugARouter = true
    override fun onCreate() {
        super.onCreate()
        //必须在init之前
        ARouter.openLog();//打印日志
        ARouter.openDebug();//线上版本需要关闭
        ARouter.init(this)
    }
}