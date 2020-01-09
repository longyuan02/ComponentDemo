package com.kt.componentdemo

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.kt.componentdemo.Utils.Constant
import kotlinx.android.synthetic.main.activity_second.*;

@Route(path = Constant.SECONDACTIVITY)
class SecondActivity : AppCompatActivity() {
    @Autowired(name = "name")
    var aRouter = ""

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_second)
        tv_second.text = aRouter
    }
}