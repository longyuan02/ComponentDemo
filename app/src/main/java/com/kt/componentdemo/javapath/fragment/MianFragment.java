package com.kt.componentdemo.javapath.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kt.componentdemo.R;

public class MianFragment extends FragmentActivity {
    private static final String FRAGMENTONE = "fragmentone";
    private static final String FRAGMENTTWO = "fragmenttwo";
    private int tag = 1;//当前标记
    private TextView tv_view1;
    private TextView tv_view2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
        tv_view1 = findViewById(R.id.tv_view_1);
        tv_view2 = findViewById(R.id.tv_view_2);
        addFragment1();
//        addFragment();
        replaceFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        Fragment fragmentone = new FragmentOne();
//        Fragment fragmenttwo = new FragmentTwo();
//        fragmentTransaction.add(fragmentone, FRAGMENTONE);
//        fragmentTransaction.add(fragmenttwo, FRAGMENTTWO);
//        fragmentTransaction.show(fragmentManager.findFragmentByTag(FRAGMENTONE));
    }

    public void replaceFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment blankFragment = new FragmentTwo();
        Bundle bundle = new Bundle();
        String param = String.valueOf(tag++);
        bundle.putString("Key", "二");
        blankFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_content, blankFragment, param);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void addFragment1() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment blankFragment = new FragmentOne();
        Bundle bundle = new Bundle();
        String param = String.valueOf(tag++);
        bundle.putString("Key", param);
        blankFragment.setArguments(bundle);
        // 在添加新的Fragment之前需要先隐藏前一个Fragment，否则会造成前后层叠
        if (fragmentManager.findFragmentByTag(String.valueOf(tag - 2)) != null) {
            fragmentTransaction.hide(fragmentManager.findFragmentByTag(String.valueOf(tag - 2)));
        }
        if (!blankFragment.isAdded()) {
            fragmentTransaction.add(R.id.fragment_content, blankFragment, param);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            fragmentTransaction.hide(blankFragment);
        } else {
            fragmentTransaction.show(blankFragment);
        }
    }

    public void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment blankFragment = new FragmentTwo();
        Bundle bundle = new Bundle();
        String param = String.valueOf(tag++);
        bundle.putString("Key", "二");
        blankFragment.setArguments(bundle);
        // 在添加新的Fragment之前需要先隐藏前一个Fragment，否则会造成前后层叠
        if (fragmentManager.findFragmentByTag(String.valueOf(tag - 2)) != null) {
            fragmentTransaction.hide(fragmentManager.findFragmentByTag(String.valueOf(tag - 2)));
        }
        if (!blankFragment.isAdded()) {
            fragmentTransaction.add(R.id.fragment_content, blankFragment, param);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else {
            fragmentTransaction.show(blankFragment);
        }
    }

    public void removeFragment(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        tag--;
    }
}
