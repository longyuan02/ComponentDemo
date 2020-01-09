package com.kt.personmodule;

import com.kt.aninterface.Display;

public class PersonDisplay implements Display {
    @Override
    public String display() {
        return "This is display in module PersonDisplay";
    }
}
