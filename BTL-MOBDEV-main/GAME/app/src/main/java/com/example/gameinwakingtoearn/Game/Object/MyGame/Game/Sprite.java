package com.example.gameinwakingtoearn.Game.Object.MyGame.Game;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;


public class Sprite {
    private final Bitmap bitmap;
    private Rect Pos;
    private Rect frame[];
    private final int height;
    private final int width;
    private final int  quatities_of_frame;
    private int currentframe=0;
    public Sprite(Context context,int id,int f,int height,int width){
        Bitmap originalBitMap = BitmapFactory.decodeResource(context.getResources(), id);
        this.height = height;
        this.width = width;
        bitmap = Bitmap.createScaledBitmap(originalBitMap, width, height, true);


        Pos=new Rect(0,0,0,0);
        quatities_of_frame=f;
        frame=new Rect[quatities_of_frame];
    }
    public void setPos(int left,int top,int right,int bottom){
        Pos.left=left;
        Pos.right=right;
        Pos.bottom=bottom;
        Pos.top=top;
    }

    public  int getHeight(){
        return height;
    }

    public  int getWidth(){
        return width;
    }
    public void setFrame(int left,int top,int right,int bottom,int i){
        frame[i]=new Rect(left,top,right,bottom);
    }
    public Bitmap getBitmap(){
        return this.bitmap;
    }

    public Rect getPos(){
        return this.Pos;
    }
    public Rect getFrame(int index){
        return this.frame[index];
    }
    public void setCurrentFrame(int i){
        this.currentframe=i;
    }
    public int getCurrentframe(){
        return this.currentframe;
    }
}

