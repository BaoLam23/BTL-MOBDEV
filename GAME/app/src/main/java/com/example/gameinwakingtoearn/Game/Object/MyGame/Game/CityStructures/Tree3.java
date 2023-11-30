package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree3InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class Tree3 extends CityStructure{
    public static final String name="tree3";
    public static final int id = R.drawable.tree_3;
    public static final double cost= 50 ;
    public static int height = 140;
    public static int width = 112;
    public Tree3(float x, float y, Context context, ArrayList<Structure> mycity, ArrayList<Structure> myDirt,
                 MyStore store, MyBag bag) {

        super(x, y, context,id ,0,height,width,name,cost,mycity,myDirt,store,bag);

    }

    @Override
    public ItemInBag changeToItemInBag() {
        return new ItemTree3InBag(this.getPosX(),this.getPosY(),this.getContext(),this.getMycity(),this.getMyDirt());
    }
}
