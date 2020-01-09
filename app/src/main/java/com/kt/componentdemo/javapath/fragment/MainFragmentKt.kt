package com.kt.componentdemo.javapath.fragment

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.kt.componentdemo.R
import kotlinx.android.synthetic.main.activity_main_fragment.*;

class MainFragmentKt : FragmentActivity(), View.OnClickListener {
    private var tag = 1
    override fun onClick(v: View?) {
        var id = v!!.id
        when (id) {
            R.id.tv_view_1 -> {
                addFragment()
            }
        }
        if (id == R.id.tv_view_1) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main_fragment)
        tv_view_1.setOnClickListener { }
    }

    fun addFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val blankFragment = FragmentTwo()
        val bundle = Bundle()
        val param = tag++.toString()
        bundle.putString("Key", param)
        blankFragment.arguments = bundle
        // 在添加新的Fragment之前需要先隐藏前一个Fragment，否则会造成前后层叠
        if (fragmentManager.findFragmentByTag((tag - 2).toString()) != null) {
            fragmentTransaction.hide(fragmentManager.findFragmentByTag((tag - 2).toString())!!)
        }
        fragmentTransaction.add(R.id.fragment_content, blankFragment, param)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}