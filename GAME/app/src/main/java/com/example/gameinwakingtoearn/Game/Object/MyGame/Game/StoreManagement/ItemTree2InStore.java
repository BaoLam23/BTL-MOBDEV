package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree2InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Tree1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Tree2;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemTree2InStore extends ItemInStore{
    public static  final  int height = 100;
    public static  final  int width = 100;
    public static final int id = R.drawable.tree_2;
    public ItemTree2InStore(float x, float y, Context context, MyBag b, ArrayList<Structure> city, ArrayList<Structure> dirt, MyStore myStore) {
        super(x, y, context,b,city,dirt, (int) Tree2.cost,myStore);
        this.addItem(x,y,id,height,width );
    }

    @Override
    public ItemInBag getItemInBagType() {
        return new ItemTree2InBag(0, 0, this.getContext(), super.city, super.dirt);
    }
}
