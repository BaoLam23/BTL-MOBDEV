package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures;


import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;


public class House1 extends CityStructure{


    public static final String name="house1";
    public static final int id = R.drawable.house_1;
    public static final double cost= 100 ;
    public static int height = 80;
    public static int width = 80;
    public House1(float x, float y, Context context, ArrayList<Structure> mycity, ArrayList<Structure> myDirt
            , MyStore store, MyBag bag) {

        super(x, y, context,id ,0,height,width,name,cost,mycity,myDirt,store,bag);

    }


    @Override
    public ItemInBag changeToItemInBag() {
        return new ItemHouse1InBag(this.getPosX(),this.getPosY(),this.getContext(),this.getMycity(),this.getMyDirt());
    }


}

