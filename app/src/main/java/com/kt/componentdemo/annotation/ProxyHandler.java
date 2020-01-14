package com.kt.componentdemo.annotation;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/**
 * button点击是事件代理
 */
public class ProxyHandler implements InvocationHandler {

    private WeakReference<Activity> mHandlerRef;

    private HashMap<String, Method> mMethodHashMap;

    public ProxyHandler(Activity activity) {
        mHandlerRef = new WeakReference<>(activity);
        mMethodHashMap = new HashMap<>();
    }

    public void mapMethod(String name, Method method) {
        mMethodHashMap.put(name, method);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Log.i(ProxyUtils.TAG, "method name = " + method.getName() + " and args = " + Arrays.toString(args));

        Object handler = mHandlerRef.get();

        if (null == handler) return null;

        String name = method.getName();

        //将onClick方法的调用映射到activity 中的InvokeBtnClick()方法
        Method realMethod = mMethodHashMap.get(name);
        if (null != realMethod) {
            return realMethod.invoke(handler, args);
        }

        return null;
    }
}