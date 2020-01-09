package com.kt.componentdemo;

import com.kt.aninterface.Display;

public class MainDisplay implements Display {
    @Override
    public String display() {
        return "this is the mainDisplay";
    }
}
