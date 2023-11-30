package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse3InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class House3 extends CityStructure{
    public static final String name="house3";
    public static final int id = R.drawable.house_3;
    public static final double cost= 200 ;
    public static int height = 150;
    public static int width = 150;
    public House3(float x, float y, Context context, ArrayList<Structure> mycity, ArrayList<Structure> myDirt,
                  MyStore store, MyBag bag) {

        super(x, y, context,id ,0,height,width,name,cost,mycity,myDirt,store,bag);

    }

    @Override
    public ItemInBag changeToItemInBag() {
        return new ItemHouse3InBag(this.getPosX(),this.getPosY(),this.getContext(),this.getMycity(),this.getMyDirt());
    }
}
