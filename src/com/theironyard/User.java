package com.theironyard;//Created by KevinBozic on 2/22/16.

import java.util.ArrayList;

public class User {
    String name;
    String password;
    ArrayList<Messages> allMessages;;

    public User (){

    }

    public User(String name){
        this.name = name;
        this.allMessages = new ArrayList<>();
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.allMessages = new ArrayList<>();
    }

    public User(String name, String password, ArrayList<Messages> messages){
        this.name = name;
        this.password = password;
        this.allMessages = messages;
    }
}