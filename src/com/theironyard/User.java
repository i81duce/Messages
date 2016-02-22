package com.theironyard;//Created by KevinBozic on 2/22/16.

import sun.plugin2.message.Message;
import java.util.ArrayList;

public class User {
    String name;
    String message;

    public User(String name) {
        this.name = name;
    }

    public User(String message, String name) {
        this.message = message;
        this.name = name;
    }

    ArrayList<Message> messages = new ArrayList<>();
}