package com.ndm.messagedemo.Item;

public class UserItem {
    private String name;
    private int resouceId;

    public UserItem(String name, int resouceId) {
        this.name = name;
        this.resouceId = resouceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResouceId() {
        return resouceId;
    }

    public void setResouceId(int resouceId) {
        this.resouceId = resouceId;
    }
}
