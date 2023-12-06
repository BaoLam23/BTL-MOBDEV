package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
    private final long levelRequired;
    private Paint paintLock = new Paint();
    private Paint paintText = new Paint();
    private String nottifyUnlock;
    private Rect lock;
    public  ItemInStore(float x, float y, Context context, MyBag b, ArrayList<Structure> city, ArrayList<Structure> dirt ,int cost,
                       MyStore myStore, long levelRequired) {
        super(x, y, context, id, height,width);
        this.bag=b;
        this.city=city;
        this.dirt = dirt;
        this.cost =cost;
        this.myStore = myStore;
        this.levelRequired = levelRequired;
        paintLock.setColor(Color.BLACK);
        paintLock.setAlpha(70);
        lock = new Rect(this.getPos().left,this.getPos().top,this.getPos().right,this.getPos().bottom);
        nottifyUnlock = "Need Level : "   + levelRequired;
        paintText.setTextSize(25);
        paintText.setColor(Color.GREEN);

    }

    @Override
    public void check_is_clicked(float x,float y){

        super.check_is_clicked(x,y);



        if(this.is_clicked){
            //add item to bag
            if(myStore.getMoney() - cost >= 0 && FireBaseMangament.getUserLevel() >= levelRequired) {
                Log.e("add new item in bag", "ok");

                bag.getBagList().addNewItem(getItemInBagType(),MyBag.posStartOfItem);
                myStore.setMoney(myStore.getMoney() - cost);
                FireBaseMangament.CreateNewUserBuilding(this.getContext());


            } else {
                Toast.makeText(this.getContext(), "không đủ tiền hoặc level", Toast.LENGTH_SHORT).show();
            }

            this.is_clicked = false;
        }

    }


    public void check_is_clickedTest(float x,float y,long userLevel){

        super.check_is_clicked(x,y);

        if(this.is_clicked){
            //add item to bag
            if(myStore.getMoney() - cost >= 0 && userLevel >= levelRequired) {

                bag.getBagList().addNewItem(getItemInBagType(),MyBag.posStartOfItem);
                myStore.setMoney(myStore.getMoney() - cost);
                FireBaseMangament.CreateNewUserBuilding(this.getContext());


            }

            this.is_clicked = false;
        }

    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(levelRequired > FireBaseMangament.getUserLevel()){
            lock.set(this.getPos().left,this.getPos().top,this.getPos().right,this.getPos().bottom);
            canvas.drawRect(lock,paintLock);
            canvas.drawText(nottifyUnlock,this.getPos().left + 4,(this.getPos().top+this.getPos().bottom)/2 - paintText.getTextSize()/2,paintText);
        }
    }

    public int getCost(){
        return this.cost;
    }
    public abstract ItemInBag getItemInBagType();

}
