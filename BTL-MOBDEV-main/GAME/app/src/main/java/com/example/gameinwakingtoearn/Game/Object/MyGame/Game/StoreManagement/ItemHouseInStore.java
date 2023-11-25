package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemHouseInStore extends ItemInStore {

    public static  final  int height = 100;
    public static  final  int width = 100;
    public ItemHouseInStore(float x, float y, Context context, MyBag b, ArrayList<Structure> city,ArrayList<ArrayList<Integer>> area ,MyStore myStore) {
        super(x, y, context,b,city,area,100,myStore);
        this.addItem(x,y, R.drawable.houseonefloor,height,width);
    }


}
