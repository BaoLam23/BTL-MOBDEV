package com.example.gameinwakingtoearn.Game.Object.Models;

public class Item {
    private int avatar, addFriend;
    private String username, level;

    public Item() {

    }

    public int getAddFriend() {
        return addFriend;
    }

    public void setAddFriend(int addFriend) {
        this.addFriend = addFriend;
    }

    public Item(int avatar, int addFriend, String username, String level) {
        this.avatar = avatar;
        this.addFriend = addFriend;
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
