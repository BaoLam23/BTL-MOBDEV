package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures;


import android.content.Context;

import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;


public class House extends Structure {


    public static final String name="house";
    public static final int id = R.drawable.houseonefloor;
    public static final double cost= 10 ;
    public static int height = 100;
    public static int width = 100;
    public House(float x, float y, Context context, ArrayList<ArrayList<Integer>> a) {

        super(x, y, context,id ,0,height,width,a,name,cost);

    }
}

