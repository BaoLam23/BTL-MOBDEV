package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList;

import android.content.Context;
import android.graphics.Canvas;


import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;

// xây dựng từng item trong một danh sách
public class AItemInList extends GameObject {

    public static final int distanceFromBorder = 30;
    private GameObject itemstored = null;


    public AItemInList(float x, float y, Context context, int id,int height,int width) {
        super(x, y, context, id, 0,height,width);

    }
    @Override
    public void setContext(Context context){
        super.setContext(context);
        if(this.itemstored != null) {
            this.itemstored.setContext(context);
        }
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(this.getImage().getBitmap(),null,this.getImage().getPos(),null);
        if(itemstored != null) {
            canvas.drawBitmap(itemstored.getImage().getBitmap(), null, itemstored.getImage().getPos(), null);
        }
    }

    public void addItem(float x,float y,int id){
         itemstored=new GameObject(x,y,this.getContext(),id,0,this.getImage().getHeight() - distanceFromBorder*2,this.getImage().getWidth() - distanceFromBorder*2);
    }

    public GameObject getItemstored(){
        return itemstored;
    }

    @Override
    public void update() {

    }
}
