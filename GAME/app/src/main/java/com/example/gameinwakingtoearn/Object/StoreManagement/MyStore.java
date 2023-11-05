package com.example.gameinwakingtoearn.Object.StoreManagement;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.gameinwakingtoearn.Game;
import com.example.gameinwakingtoearn.Object.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Object.CityStructures.Structure;
import com.example.gameinwakingtoearn.Object.GameObject;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class MyStore extends GameObject {
    private StoreItemList itemList;

    public MyStore(float x, float y, Context context, MyBag bag, ArrayList<Structure> city, int area[][]) {
        super(x, y, context, R.drawable.store,2,0);
        itemList=new StoreItemList(context,30,200,bag,city,area);
       this.image.setFrame(0,0,40,39,0);
       this.image.setFrame(40*4+7*3,0,40*4+7*3+40*4,39*4,1);

    }

    @Override
    public void draw(Canvas canvas) {

        if(this.is_clicked) {
            itemList.draw(canvas);
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


}
