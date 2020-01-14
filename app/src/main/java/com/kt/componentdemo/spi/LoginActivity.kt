package com.kt.componentdemo.spi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kt.componentdemo.proxy.Fly
import com.kt.componentdemo.proxy.ProxyAnimal

class LoginActivity : ProxyAnimal(), Fly {
    private val tag: String = javaClass.simpleName
    override fun fly() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.e(tag, "====loginactivity");
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}