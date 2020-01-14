package com.kt.componentdemo.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/*动态代理实现类*/
public class DyProxyHandler implements InvocationHandler {
    private Fly fly;

    public DyProxyHandler(Fly fly) {
        this.fly = fly;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e("invoke=====", method.getName() + "******" + args);
        return method.invoke(fly, args);
    }
}
