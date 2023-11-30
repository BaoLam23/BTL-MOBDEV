package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse2InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class House2 extends CityStructure{
    public static final String name="house2";
    public static final int id = R.drawable.house_2;
    public static final double cost= 150 ;
    public static int height = 100;
    public static int width = 100;
    public House2(float x, float y, Context context, ArrayList<Structure> mycity, ArrayList<Structure> myDirt,
                  MyStore store, MyBag bag) {

        super(x, y, context,id ,0,height,width,name,cost,mycity,myDirt,store,bag);

    }

    @Override
    public ItemInBag changeToItemInBag() {
        return new ItemHouse2InBag(this.getPosX(),this.getPosY(),this.getContext(),this.getMycity(),this.getMyDirt());
    }
}
