package com.example.gameinwakingtoearn.Game.Object.MyGame.Game;

public class Building {
    private String id;
    private String name;
    private int x;
    private int y;
    private double cost;

    public static final boolean inMap = true;
    public static final boolean inBag = false;

    private boolean status;

    public Building() {
    }

    public Building(int x,int y, double cost, String name,String id) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.name = name;
        this.id = id;
    }



    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
