package com.chengtao.culture.entity;

import com.chengtao.library.entity.BaseResponse;

/**
 * Created by ChengTao on 2016-12-19.
 */

public class User extends BaseResponse {
    private String name;
    private String password;
    private int type;
    public User(){

    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
