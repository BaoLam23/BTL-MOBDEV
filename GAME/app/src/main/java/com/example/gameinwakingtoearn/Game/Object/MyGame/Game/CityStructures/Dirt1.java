package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemDirt1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class Dirt1 extends Dirt{

    public static final String name="dirt1";
    public static final int id = R.drawable.grass_dark_1;
    public static final double cost= 50 ;
    public static int height = 100;
    public static int width = 100;
    public Dirt1(float x, float y, Context context, ArrayList<Structure> mycity, ArrayList<Structure> myDirt,
            MyStore store, MyBag bag) {
        super(x, y, context, id, name, cost,mycity,myDirt,store,bag);
    }

    @Override
    public ItemInBag changeToItemInBag() {
        return new ItemDirt1InBag(this.getPosX(),this.getPosY(),this.getContext(),this.getMycity(),this.getMyDirt());
    }
}
