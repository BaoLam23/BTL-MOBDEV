package com.example.gameinwakingtoearn.Game.Object.Models;

public class FriendReqItem {

    private String id;
    private int avatar, declineIcon, acceptIcon, xpIcon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String username;
    private String level;

    public FriendReqItem() {}

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getDeclineIcon() {
        return declineIcon;
    }

    public void setDeclineIcon(int declineIcon) {
        this.declineIcon = declineIcon;
    }

    public int getAcceptIcon() {
        return acceptIcon;
    }

    public void setAcceptIcon(int acceptIcon) {
        this.acceptIcon = acceptIcon;
    }

    public int getXpIcon() {
        return xpIcon;
    }

    public void setXpIcon(int xpIcon) {
        this.xpIcon = xpIcon;
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

    public FriendReqItem(String id, int avatar, int declineIcon, int acceptIcon, int xpIcon, String username, String level) {
        this.id = id;
        this.avatar = avatar;
        this.declineIcon = declineIcon;
        this.acceptIcon = acceptIcon;
        this.xpIcon = xpIcon;
        this.username = username;
        this.level = level;
    }
}
