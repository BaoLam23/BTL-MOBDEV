package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House2;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemHouse2InBag extends ItemInBag{
    public static final int height = House2.height;
    public static final int width = House2.width;
    public static final int id = R.drawable.house_2;
    public ItemHouse2InBag(float x, float y, Context context, ArrayList<Structure> city, ArrayList<Structure> dirt) {
        super(x, y, context,city,dirt);
        this.addItem(x,y,id ,height,width);
    }


    @Override
    public void addSymbolStruture() {
        this.structure = new GameObject(x,y,this.getContext(),id,0,height,width);
    }

    @Override
    public Structure createStructure(float x, float y, ArrayList<Structure> city, ArrayList<Structure> dirt,
                                     MyStore store, MyBag bag) {
        return new House2(x,y,this.getContext(),city,dirt,store,bag);
    }
}
