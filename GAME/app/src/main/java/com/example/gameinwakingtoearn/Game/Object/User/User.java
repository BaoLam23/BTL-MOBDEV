package com.example.gameinwakingtoearn.Game.Object.User;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String uid;
    private String email;
    private String username;
    private double money;
    List<String> buildings = new ArrayList<>();
    private double totalDistance;
    List<String> friendList = new ArrayList<>();

    private int level;
    private int currentExp;

    public int getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(int currentExp) {
        this.currentExp = currentExp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<String> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<String> buildings) {
        this.buildings = buildings;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public List<String> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<String> friendList) {
        this.friendList = friendList;
    }
}

//    User currentUser = CurrentUser.getInstance().getUser();
//currentUser.setMoney(newMoneyValue);

