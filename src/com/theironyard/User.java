package com.theironyard;//Created by KevinBozic on 2/22/16.

import java.util.ArrayList;

public class User {
    String name;
    ArrayList<Messages> allMessages = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }
}