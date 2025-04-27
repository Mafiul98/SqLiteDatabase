package com.example.sqlitedatabase;

public class User {

    int id;
    String name;
    String mobile;

    public User(int id,String name,String mobile){
        this.id = id;
        this.name = name;
        this.mobile = mobile;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }
}
