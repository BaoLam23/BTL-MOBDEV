package com.example.gameinwakingtoearn.Game.Object.Models;

public class Item {
    private int avatar, xpIcon, addFriend;
    private String username, level;

    public Item() {

    }

    public int getAddFriend() {
        return addFriend;
    }

    public void setAddFriend(int addFriend) {
        this.addFriend = addFriend;
    }

    public Item(int avatar,int xpIcon, int addFriend, String username, String level) {
        this.avatar = avatar;
        this.xpIcon = xpIcon;
        this.addFriend = addFriend;
        this.username = username;
        this.level = level;
    }

    public int getXpIcon() {
        return xpIcon;
    }

    public void setXpIcon(int xpIcon) {
        this.xpIcon = xpIcon;
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