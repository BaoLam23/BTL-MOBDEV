package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemDirt1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemDirt1InStore extends ItemInStore{

    public static final int id = R.drawable.grass_dark_1;
    public static final long levelRequired = 1;
    public ItemDirt1InStore (float x, float y, Context context, MyBag b, ArrayList<Structure> city, ArrayList<Structure> dirt  , MyStore myStore) {
        super(x, y, context,b,city,dirt, (int) House1.cost,myStore,levelRequired);
        this.addItem(50,200,id);
    }


    @Override
    public ItemInBag getItemInBagType() {
        return new ItemDirt1InBag(0, 0, this.getContext(), super.city, super.dirt);
    }
}
