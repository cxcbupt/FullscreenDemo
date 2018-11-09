package com.example.cxc.fullscreendemo.decoration.po;

import java.io.Serializable;

public class ItemPO implements Serializable {
    private static final long serialVersionUID = 7879752396036182745L;

    private String name;
    private int age;

    public ItemPO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
