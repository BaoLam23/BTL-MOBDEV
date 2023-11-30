package com.example.gameinwakingtoearn.Game.Object.MyGame.Game;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;


public class Sprite {
    private  Bitmap bitmap;
    private final int id;
    private Rect Pos;
    private Rect frame[];
    private final int height;
    private final int width;
    private final int  quatities_of_frame;
    private int currentframe=0;
    private Context context;
    public Sprite(Context context,int id,int f,int height,int width){
        this.height = height;
        this.width = width;
        this.id= id;
        Pos = new Rect(0, 0, 0, 0);
        quatities_of_frame = f;
        frame = new Rect[quatities_of_frame];
        this.context = context;
        if(context !=null) {
            Bitmap originalBitMap = BitmapFactory.decodeResource(context.getResources(), id);

            bitmap = Bitmap.createScaledBitmap(originalBitMap, width, height, true);

        } else{
            bitmap = null;
        }
    }

    public void setContext(Context context){
        Bitmap originalBitMap = BitmapFactory.decodeResource(context.getResources(), id);
        this.context = context;

        bitmap = Bitmap.createScaledBitmap(originalBitMap, width, height, true);
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

    public Context getContext() {
        return this.context;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(this.getBitmap(),null,this.getPos(),null);
    }
}

