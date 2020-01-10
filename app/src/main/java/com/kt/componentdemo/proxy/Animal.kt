package com.kt.componentdemo.proxy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kt.componentdemo.R
import kotlinx.android.synthetic.main.activity_isp_main.*

class Animal : AppCompatActivity(), Fly {
    val TAG = javaClass.simpleName
    override fun fly() {
        Log.e(TAG, "======Animal")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_isp_main)
        tv_isp.text = "annimal"
    }
}