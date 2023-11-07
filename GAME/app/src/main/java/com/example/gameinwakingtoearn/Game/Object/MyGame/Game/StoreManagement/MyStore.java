package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class MyStore extends GameObject {
    private StoreItemList itemList;

    private long money=0;
    private Paint paintText;

    private void drawMoney(Canvas canvas){
        canvas.drawText("Your money : " + String.valueOf(money),itemList.getBackground().left
                ,itemList.getBackground().top+100,paintText);
    }

    public MyStore(float x, float y, Context context, MyBag bag, ArrayList<Structure> city, int area[][], long money) {
        super(x, y, context, R.drawable.store,2,0);
        itemList=new StoreItemList(context,30,200,bag,city,area,this);
       this.image.setFrame(0,0,40,39,0);
       this.image.setFrame(40*4+7*3,0,40*4+7*3+40*4,39*4,1);
       this.paintText=new Paint();
       paintText.setTextSize(30);
       paintText.setColor(Color.BLACK);
       this.money = money;
    }

    @Override
    public void draw(Canvas canvas) {

        if(this.is_clicked) {
            itemList.draw(canvas);
            drawMoney(canvas);
        } else {
            canvas.drawBitmap(this.image.getBitmap(),this.image.getFrame(0),this.image.getPos(),null);
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
