package com.example.gameinwakingtoearn.Object.StoreManagement;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.gameinwakingtoearn.Object.BagManagement.BagList;
import com.example.gameinwakingtoearn.Object.BagManagement.ItemHouseInBag;
import com.example.gameinwakingtoearn.Object.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Object.CityStructures.Structure;
import com.example.gameinwakingtoearn.Object.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemHouseInStore extends ItemInStore {

    public ItemHouseInStore(float x, float y, Context context, MyBag b, ArrayList<Structure> city, int area[][]) {
        super(x, y, context,b,city,area);
        this.addItem(x,y, R.drawable.houseonefloor,0);
    }


}
