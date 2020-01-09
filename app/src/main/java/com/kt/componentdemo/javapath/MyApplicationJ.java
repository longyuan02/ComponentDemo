package com.kt.componentdemo.javapath;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class MyApplicationJ extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();//打印日志
        ARouter.openDebug();//线上版本需要关闭
        ARouter.init(this);
    }
}
