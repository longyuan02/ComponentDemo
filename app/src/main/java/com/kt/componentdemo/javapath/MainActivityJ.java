package com.kt.componentdemo.javapath;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kt.aninterface.Display;
import com.kt.componentdemo.MainActivity;
import com.kt.componentdemo.R;
import com.kt.componentdemo.Utils.Constant;

import java.util.Iterator;
import java.util.ServiceLoader;

public class MainActivityJ extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadModule();
//                ARouter.getInstance().build(Constant.SECONDACTIVITY)
//                        .withString("name", "hello 我是ARouter")
//                        .withString("key3", "888")
//                        .navigation(MainActivityJ.this, new NavigationCallback() {
//                            @Override
//                            public void onFound(Postcard postcard) {
//                                Log.e(TAG, "路由目标发现" + postcard.getPath());
//                            }
//
//                            @Override
//                            public void onLost(Postcard postcard) {
//                                Log.e(TAG, "路由目标丢失" + postcard.getPath());
//                            }
//
//                            @Override
//                            public void onArrival(Postcard postcard) {
//                                Log.e(TAG, "路由目标跳转" + postcard.getPath());
//                            }
//
//                            @Override
//                            public void onInterrupt(Postcard postcard) {
//                                Log.e(TAG, "路由目标被拦截" + postcard.getPath());
//                            }
//                        });
//                finish();
            }
        });
    }

    private void loadModule() {
        ServiceLoader<Display> loader = ServiceLoader.load(Display.class);
        Iterator<Display> mIterator = loader.iterator();
        while (mIterator.hasNext()) {
//            mIterator.next().display();
            Log.e("Log====", mIterator.next().display());
        }
    }
}
