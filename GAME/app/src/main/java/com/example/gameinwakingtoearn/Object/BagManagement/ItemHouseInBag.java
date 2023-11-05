package com.example.gameinwakingtoearn.Object.BagManagement;

import android.content.Context;

import com.example.gameinwakingtoearn.Object.CityStructures.House;
import com.example.gameinwakingtoearn.Object.CityStructures.Structure;
import com.example.gameinwakingtoearn.Object.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemHouseInBag extends ItemInBag {
    public ItemHouseInBag(float x, float y, Context context, ArrayList<Structure> city, int area[][]) {
        super(x, y, context,city,area);
        this.addItem(x,y, R.drawable.houseonefloor,zoom);
        this.structure=new House(200,300,context,this.area);
    }




}
