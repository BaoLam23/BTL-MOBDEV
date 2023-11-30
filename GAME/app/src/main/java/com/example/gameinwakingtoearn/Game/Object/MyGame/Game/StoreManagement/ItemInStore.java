package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.FireBaseMangament;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public  abstract class ItemInStore extends AItemInList {


    protected ArrayList<Structure> city;
    protected ArrayList<Structure> dirt;

    protected MyBag bag;
    protected MyStore myStore;
    private final int cost;

    public static  final int height = 180;
    public static  final int width = 180;
    public static final  int id =  R.drawable.item_box;
    public  ItemInStore(float x, float y, Context context, MyBag b, ArrayList<Structure> city, ArrayList<Structure> dirt ,int cost,
                       MyStore myStore) {
        super(x, y, context, id, height,width);
        this.bag=b;
        this.city=city;
        this.dirt = dirt;
        this.cost =cost;
        this.myStore = myStore;
    }

    @Override
    public void check_is_clicked(float x,float y){

        super.check_is_clicked(x,y);



        if(this.is_clicked){
            //add item to bag
            if(myStore.getMoney() - cost >= 0) {
                Log.e("add new item in bag", "ok");

                bag.getBagList().addNewItem(getItemInBagType(),MyBag.posStartOfItem);
                myStore.setMoney(myStore.getMoney() - cost);
                FireBaseMangament.CreateNewUserBuilding(this.getContext());


            } else {
                Toast.makeText(this.getContext(), "không đủ tiền", Toast.LENGTH_SHORT).show();
            }

            this.is_clicked = false;
        }

    }

    public int getCost(){
        return this.cost;
    }
    public abstract ItemInBag getItemInBagType();

}
