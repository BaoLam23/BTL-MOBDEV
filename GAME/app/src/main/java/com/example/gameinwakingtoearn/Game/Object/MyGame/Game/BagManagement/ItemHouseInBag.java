package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemHouseInBag extends ItemInBag {
    public ItemHouseInBag(float x, float y, Context context, ArrayList<Structure> city, int area[][]) {
        super(x, y, context,city,area);
        this.addItem(x,y, R.drawable.houseonefloor,zoom);
    }


    @Override
    public void addSymbolStruture() {
        this.structure = new GameObject(x,y,this.context,R.drawable.houseonefloor,0,zoom);
    }

    @Override
    public Structure createStructure(float x,float y,int a[][]) {
        return new House(x,y,context,a);
    }
}
