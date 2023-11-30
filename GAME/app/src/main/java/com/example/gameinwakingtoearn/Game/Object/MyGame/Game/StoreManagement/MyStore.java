package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class MyStore extends GameObject {
    private StoreItemList itemList;

    private long money=0;
    private Paint paintText;

    public static  int height = 150;
    public static  int width = 150;
    public static final int id = R.drawable.shop_button;


    private void drawMoney(Canvas canvas){
        canvas.drawText( String.valueOf(money),itemList.getMoneyBar().getPos().left + 58*itemList.getMoneyBar().getWidth()/136
                ,itemList.getMoneyBar().getPos().top + (itemList.getMoneyBar().getPos().bottom - itemList.getMoneyBar().getPos().top)/2 - paintText.getTextSize()/2 + 10
                ,paintText);
    }

    public MyStore(float x, float y, Context context, MyBag bag, ArrayList<Structure> city, ArrayList<Structure> dirt , long money) {
        super(x, y, context,id,0,height,width);
        itemList=new StoreItemList(context,x,y,bag,city,dirt,this);

       this.paintText=new Paint();
       paintText.setTextSize(30);
       paintText.setColor(Color.BLACK);
//        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "karantina_regular.ttf");
//        paintText.setTypeface(typeface);
       this.money = money;

    }



    @Override
    public void draw(Canvas canvas) {

        if(this.is_clicked) {
            itemList.draw(canvas);
            drawMoney(canvas);
        } else {
            super.draw(canvas);
        }
    }

    @Override
    public void check_is_clicked(float x, float y) {
        if(x<=this.image.getPos().right && x>=this.image.getPos().left &&
                y<=this.image.getPos().bottom && y>=this.image.getPos().top){
            this.set_is_clicked(true);
        }
        if(this.is_clicked) {
            itemList.checkIsClicked(x, y);
            if(itemList.getIs_quit()){
                this.is_clicked=false;
                itemList.setIs_quit(false);
            }
        }
    }

    public void setMoney(long money){
        this.money =money;
    }
    public long getMoney(){
        return this.money;
    }


}
