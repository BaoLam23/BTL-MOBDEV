package com.example.gameinwakingtoearn.Object.StoreManagement;

import android.content.Context;
import android.util.Log;

import com.example.gameinwakingtoearn.Object.BagManagement.ItemHouseInBag;
import com.example.gameinwakingtoearn.Object.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Object.CityStructures.Structure;
import com.example.gameinwakingtoearn.Object.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemInStore extends AItemInList {
    private ArrayList<Structure> city;
    private int area[][];
    private MyBag bag;
    public ItemInStore(float x, float y, Context context, MyBag b, ArrayList<Structure> city, int area[][]) {
        super(x, y, context, R.drawable.icon_item_in_myteam, 100);
        this.bag=b;
        this.city=city;
        this.area=area;
    }

    @Override
    public void check_is_clicked(float x,float y){

        super.check_is_clicked(x,y);
        if(this.is_clicked){
            //add item to bag
            Log.e("add new item in bag","ok");
            bag.getBagList().addNewItem(new ItemHouseInBag(0,0,context,city,area),-400);
            this.is_clicked=false;
        }

    }
}
