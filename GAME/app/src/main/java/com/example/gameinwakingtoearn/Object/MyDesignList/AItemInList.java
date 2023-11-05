package com.example.gameinwakingtoearn.Object.MyDesignList;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.gameinwakingtoearn.Object.GameObject;

public class AItemInList extends GameObject {
    GameObject itemstored = null;
    public AItemInList(float x, float y, Context context, int id, int zoom) {
        super(x, y, context, id, 0, zoom);
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(this.getImage().getBitmap(),null,this.getImage().getPos(),null);
        if(itemstored != null) {
            canvas.drawBitmap(itemstored.getImage().getBitmap(), null, itemstored.getImage().getPos(), null);
        }
    }

    public void addItem(float x,float y,int id,int zoom){
         itemstored=new GameObject(x,y,this.context,id,0,zoom);
    }

    public GameObject getItemstored(){
        return itemstored;
    }

    @Override
    public void update() {

    }
}
