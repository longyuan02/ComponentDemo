package com.kt.componentdemo.javapath;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kt.componentdemo.R;
import com.kt.componentdemo.Utils.Constant;
import com.kt.componentdemo.javapath.fragment.MianFragment;

@Route(path = Constant.SECONDACTIVITY)
public class SecondActivityJ extends AppCompatActivity {
    @Autowired(name = "name")
    public String name;
    @Autowired
    public String key3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_second);
        TextView tv_second = findViewById(R.id.tv_second);
        Log.d("param", "name:" + name + "key:" + key3);
        tv_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivityJ.this, MianFragment.class));
            }
        });
    }
}
