package com.example.gameinwakingtoearn.Object.BagManagement;

import android.content.Context;
import android.graphics.Canvas;

import com.example.gameinwakingtoearn.Object.CityStructures.Structure;
import com.example.gameinwakingtoearn.Object.GameObject;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class MyBag extends GameObject {
    private BagList itemlist;
    public MyBag(float x, float y, Context context, ArrayList<Structure> mycity,int area[][]) {
        super(x, y, context, R.drawable.store,0, 0);
        itemlist=new BagList(context,x,y);
    }
    public void draw(Canvas canvas){
        if(this.is_clicked){
            itemlist.draw(canvas);
        }
        else{
            canvas.drawBitmap(this.image.getBitmap(),null,this.image.getPos(),null);
        }
    }

    @Override
    public void check_is_clicked(float x, float y) {
        if(x<=this.image.getPos().right && x>=this.image.getPos().left &&
                y<=this.image.getPos().bottom && y>=this.image.getPos().top){
            this.set_is_clicked(true);
        }
        if(this.is_clicked) {
            itemlist.checkIsClicked(x, y);
            if(itemlist.getIs_quit()){
                this.is_clicked=false;
                itemlist.setIs_quit(false);
            }
        }
    }

    public BagList getBagList(){
        return this.itemlist;
    }

}
