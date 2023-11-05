package com.example.gameinwakingtoearn.Object.BagManagement;

import android.content.Context;
import android.util.Log;

import com.example.gameinwakingtoearn.Object.CityStructures.Structure;
import com.example.gameinwakingtoearn.Object.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemInBag extends AItemInList {
    private ArrayList<Structure> city;
    protected int area[][];
    protected Structure structure;
    private int repeat=0;

    public ItemInBag(float x, float y, Context context,ArrayList<Structure> city,int area[][]) {
        super(x, y, context, R.drawable.icon_item_in_myteam, 10);
        this.city=city;
        this.area=area;

    }

    @Override
    public void check_is_clicked(float x,float y){
        super.check_is_clicked(x,y);
        if(this.is_clicked && repeat == 0 ){
            Log.e("new structure added to our city","ok");
            this.city.add(structure);
            repeat++;
            Log.e("quatities of mycity :",this.city.size()+"");
        }
    }



}
