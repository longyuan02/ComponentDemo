package com.kt.componentdemo.annotation;

import android.app.Activity;
import android.util.Log;
import android.view.View;


import com.kt.componentdemo.proxy.ProxyHandler;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtils {
    private static ProxyUtils proxyUtils;

    public static ProxyUtils getInstance(Activity activity) {
        proxyUtils = new ProxyUtils();
        injectView(activity);
        injectEvent(activity);
        return proxyUtils;
    }

    public static final String TAG = ProxyUtils.class.getSimpleName();

    private static void injectView(Activity activity) {
        if (null == activity) return;
        Class<? extends Activity> activityClass = activity.getClass();
        Field[] declaredFields = activityClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(InjectView.class)) {
                //找到InjectView注解的field
                InjectView annotation = field.getAnnotation(InjectView.class);
                //找到button的id
                int value = annotation.value();
                try {
                    //找到findViewById方法
                    Method findViewByIdMethod = activityClass.getMethod("findViewById", int.class);
                    findViewByIdMethod.setAccessible(true);
                    findViewByIdMethod.invoke(activity, value);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectEvent(Activity activity) {
        if (null == activity) {
            return;
        }

        Class<? extends Activity> activityClass = activity.getClass();
        Method[] declaredMethods = activityClass.getDeclaredMethods();

        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(onClick.class)) {
                Log.i(ProxyUtils.TAG, method.getName());
                onClick annotation = method.getAnnotation(onClick.class);
                //获取button id
                int[] value = annotation.value();
                //获取EventType
                EventType eventType = annotation.annotationType().getAnnotation(EventType.class);
                Class listenerType = eventType.listenerType();
                String listenerSetter = eventType.listenerSetter();
                String methodName = eventType.methodName();

                //创建InvocationHandler和动态代理(代理要实现listenerType，这个例子就是处理onClick点击事件)
                ProxyHandler proxyHandler = new ProxyHandler(activity);
                Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, proxyHandler);

                proxyHandler.mapMethod(methodName, method);
                try {
                    for (int id : value) {
                        //找到Button
                        Method findViewByIdMethod = activityClass.getMethod("findViewById", int.class);
                        findViewByIdMethod.setAccessible(true);
                        View btn = (View) findViewByIdMethod.invoke(activity, id);
                        //根据listenerSetter方法名和listenerType方法参数找到method
                        Method listenerSetMethod = btn.getClass().getMethod(listenerSetter, listenerType);
                        listenerSetMethod.setAccessible(true);
                        listenerSetMethod.invoke(btn, listener);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//    public static void generateClassFile(Class clazz,String proxyName)
//    {
//        //根据类信息和提供的代理类名称，生成字节码
//        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
//        String paths = clazz.getResource(".").getPath();
//        System.out.println(paths);
//        FileOutputStream out = null;
//
//        try {
//            //保留到硬盘中
//            out = new FileOutputStream(paths+proxyName + ".class");
//            out.write(classFile);
//            out.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
