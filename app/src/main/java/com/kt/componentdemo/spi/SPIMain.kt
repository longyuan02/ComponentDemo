package com.kt.componentdemo.spi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kt.aninterface.Display
import com.kt.componentdemo.R
import com.kt.componentdemo.proxy.NullClass
import com.kt.componentdemo.proxy.ProxyHandler
import java.util.*
import kotlinx.android.synthetic.main.activity_isp_main.*
import com.kt.componentdemo.proxy.Fly
import java.lang.reflect.Proxy


class SPIMain : AppCompatActivity(), Fly {
    override fun fly() {
        Log.e("ISPmain=====", "代理调用")
    }

    var nullClass: NullClass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_isp_main)
        tv_isp.setOnClickListener {
            GetProxyLoader();
            //可空对象不会报错
            Log.e("click=====", "??" + nullClass?.name)
        }
        tv_isp_two.setOnClickListener {
            loadModule()
            //报错空指针
//            Log.e("click=====", "!" + nullClass!!.name)
        }
    }

    private fun loadModule() {
        val loader = ServiceLoader.load(Display::class.java)
        val mIterator = loader.iterator()
        while (mIterator.hasNext()) {
            Log.e("Log====", mIterator.next().display() + "---" + mIterator.javaClass.simpleName)
        }
    }

    private fun GetProxyLoader() {
        var classLoader = javaClass.classLoader
        var interfaces = javaClass.interfaces
        var proxyHandler = ProxyHandler(this)
        val newProxyInstance = Proxy.newProxyInstance(classLoader, interfaces, proxyHandler)
        val fly = newProxyInstance as Fly
        fly.fly()
    }
}