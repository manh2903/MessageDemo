package com.ndm.messagedemo.Item;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "chat")
public class Chat {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String text;
    private boolean isSelf;

    public Chat(String username, String text) {
        this.username = username;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Chat other = (Chat) obj;
        return id == other.id &&
                Objects.equals(username, other.username) &&
                Objects.equals(text, other.text) &&
                isSelf == other.isSelf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, text, isSelf);
    }
}
