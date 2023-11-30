package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Tree1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Tree2;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemTree1InBag extends ItemInBag{
    public static final int height = Tree1.height;
    public static final int width = Tree1.width;
    public static final int id = R.drawable.tree_1;
    public  ItemTree1InBag(float x, float y, Context context, ArrayList<Structure> city, ArrayList<Structure> dirt ) {
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
        return new Tree1(x,y,this.getContext(),city,dirt,store,bag);
    }
}
