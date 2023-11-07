package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemHouseInStore extends ItemInStore {

    public ItemHouseInStore(float x, float y, Context context, MyBag b, ArrayList<Structure> city, int area[][],MyStore myStore) {
        super(x, y, context,b,city,area,100,myStore);
        this.addItem(x,y, R.drawable.houseonefloor,0);
    }


}
