package com.kt.componentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.kt.componentdemo.Utils.Constant
import kotlinx.android.synthetic.main.activity_main.*;
import com.kt.aninterface.Display
import java.util.*


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ARouter.init(application)
        tv.setOnClickListener {
            //            loadModule()
            ARouter.getInstance().build(Constant.LOGINACTIVITY)
                .withString("name", "hello 我是ARouter")
                .navigation(this@MainActivity, object : NavigationCallback {
                    override fun onLost(postcard: Postcard?) {
                        //失败时调用
                        Log.e(TAG, "路由loss" + postcard?.path)
                    }

                    override fun onFound(postcard: Postcard?) {
                        Log.e(TAG, "路由onFound" + postcard?.path)
                    }

                    override fun onInterrupt(postcard: Postcard?) {
                        Log.e(TAG, "路由onInterrupt" + postcard?.path)
                    }

                    override fun onArrival(postcard: Postcard?) {
                        Log.e(TAG, "路由onArrival" + postcard?.path)
                    }

                })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        persistentState?.putPersistableBundle("put", persistentState)
    }

    fun loadModule() {
        var displa1 = MainDisplay()
        displa1.display()
        val loader = ServiceLoader.load(Display::class.java)
        var mIterator = loader.iterator()
        while (mIterator.hasNext()) {
            Log.e("Log====", mIterator.next().display())
        }
    }
}
