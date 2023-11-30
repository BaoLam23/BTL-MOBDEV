package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree4InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class Tree4 extends CityStructure{
    public static final String name="tree4";
    public static final int id = R.drawable.tree_4;
    public static final double cost= 150 ;
    public static int height = 246;
    public static int width = 160;
    public Tree4(float x, float y, Context context, ArrayList<Structure> mycity, ArrayList<Structure> myDirt,
                 MyStore store, MyBag bag) {

        super(x, y, context,id ,0,height,width,name,cost,mycity,myDirt,store,bag);

    }

    @Override
    public ItemInBag changeToItemInBag() {
        return new ItemTree4InBag(this.getPosX(),this.getPosY(),this.getContext(),this.getMycity(),this.getMyDirt());
    }
}
