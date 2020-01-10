package com.kt.componentdemo.javapath;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kt.aninterface.Display;
import com.kt.componentdemo.MainActivity;
import com.kt.componentdemo.R;
import com.kt.componentdemo.Utils.Constant;
import com.kt.componentdemo.annotation.InjectView;
import com.kt.componentdemo.annotation.ProxyUtils;
import com.kt.componentdemo.annotation.onClick;
import com.kt.componentdemo.isp.ISPMain;

import java.util.Iterator;
import java.util.ServiceLoader;

public class MainActivityJ extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    @InjectView(R.id.tv)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProxyUtils.getInstance(this);
    }

    /*跳转路由*/
    private void ArouterJump() {
        ARouter.getInstance().build(Constant.SECONDACTIVITY)
                .withString("name", "hello 我是ARouter")
                .withString("key3", "888")
                .navigation(MainActivityJ.this, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        Log.e(TAG, "路由目标发现" + postcard.getPath());
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.e(TAG, "路由目标丢失" + postcard.getPath());
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.e(TAG, "路由目标跳转" + postcard.getPath());
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.e(TAG, "路由目标被拦截" + postcard.getPath());
                    }
                });
    }

    /*SPI*/
    private void loadModule() {
        ServiceLoader<Display> loader = ServiceLoader.load(Display.class);
        Iterator<Display> mIterator = loader.iterator();
        while (mIterator.hasNext()) {
            Log.e("Log====", mIterator.next().display());
        }
    }

    @onClick({R.id.tv})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
//                ArouterJump();
                Intent intent = new Intent(MainActivityJ.this, ISPMain.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
