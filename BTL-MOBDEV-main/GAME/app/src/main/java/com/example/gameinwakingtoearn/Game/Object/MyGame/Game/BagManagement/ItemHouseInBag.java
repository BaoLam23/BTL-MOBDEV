package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemHouseInBag extends ItemInBag {
    public static final int height = 100;
    public static final int width = 100;
    public ItemHouseInBag(float x, float y, Context context, ArrayList<Structure> city, ArrayList<ArrayList<Integer>> area ) {
        super(x, y, context,city,area);
        this.addItem(x,y, R.drawable.houseonefloor,height,width);
    }


    @Override
    public void addSymbolStruture() {
        this.structure = new GameObject(x,y,this.context,R.drawable.houseonefloor,0,height,width);
    }

    @Override
    public Structure createStructure(float x,float y,ArrayList<ArrayList<Integer>> area) {
        return new House(x,y,context,area);
    }
}
