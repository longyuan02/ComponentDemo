package com.kt.componentdemo.spi;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kt.componentdemo.R;
import com.kt.componentdemo.proxy.DyProxyHandler;
import com.kt.componentdemo.proxy.Fly;
import com.kt.componentdemo.proxy.ProxyAnimal;
import com.kt.componentdemo.annotation.ProxyHandler;

import java.lang.reflect.Proxy;

public class SPIMainJ extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isp_main);
        findViewById(R.id.tv_isp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetProxyLoader();
            }
        });
    }

    private void GetProxyLoader() {
        ProxyAnimal animal = new ProxyAnimal();
        ClassLoader classLoader = animal.getClass().getClassLoader();
        Class[] interfaces = animal.getClass().getInterfaces();
        DyProxyHandler proxyHandler = new DyProxyHandler(animal);
//      ProxyUtils.generateClassFile(animal.getClass(), "AnimalProxy");
        Object newProxyInstance = Proxy.newProxyInstance(classLoader, interfaces, proxyHandler);
        Fly fly = (Fly) newProxyInstance;
        fly.fly();
    }
}
