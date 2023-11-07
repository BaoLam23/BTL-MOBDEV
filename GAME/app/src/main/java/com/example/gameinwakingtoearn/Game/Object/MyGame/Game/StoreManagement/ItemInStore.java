package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouseInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class ItemInStore extends AItemInList {
    private ArrayList<Structure> city;
    private int area[][];
    private MyBag bag;
    private MyStore myStore;
    private final int cost;
    public ItemInStore(float x, float y, Context context, MyBag b, ArrayList<Structure> city, int area[][],int cost,
                       MyStore myStore) {
        super(x, y, context, R.drawable.icon_item_in_myteam, 100);
        this.bag=b;
        this.city=city;
        this.area=area;
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
                bag.getBagList().addNewItem(new ItemHouseInBag(0, 0, context, city, area), -400);
                myStore.setMoney(myStore.getMoney() - cost);
            }else{
                Toast.makeText(context, "Money is not enough", Toast.LENGTH_SHORT).show();
            }

            this.is_clicked = false;
        }

    }

    public int getCost(){
        return this.cost;
    }
}
