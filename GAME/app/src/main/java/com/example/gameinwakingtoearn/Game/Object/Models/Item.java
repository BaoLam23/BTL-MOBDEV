package com.example.gameinwakingtoearn.Game.Object.Models;

public class Item {
    private int avatar;
    private String username, level;

    public Item() {

    }

    public Item(int avatar, String username, String level) {
        this.avatar = avatar;
        this.username = username;
        this.level = level;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
