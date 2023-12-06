package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse3InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House3;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemHouse3InStore extends ItemInStore{

    public static final int id = R.drawable.house_3;
    public static final long levelRequired = 10;
    public ItemHouse3InStore(float x, float y, Context context, MyBag b, ArrayList<Structure> city, ArrayList<Structure> dirt , MyStore myStore) {
        super(x, y, context,b,city,dirt, (int) House3.cost,myStore,levelRequired);
        this.addItem(x,y,id);
    }

    @Override
    public ItemInBag getItemInBagType() {
        return new ItemHouse3InBag(0, 0, this.getContext(), super.city, super.dirt);
    }
}
