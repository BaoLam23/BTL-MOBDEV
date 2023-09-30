package com.example.testmap.MyGame.Object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Sprite {
    private final Bitmap bitmap;
    private Rect Pos;
    private Rect frame[];
    private final int  quatities_of_frame;
    public Sprite(Context context,int id,int f){
        bitmap= BitmapFactory.decodeResource(context.getResources(), id);
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
    public void setFrame(int left,int top,int right,int bottom,int i){
        frame[i].left=left;
        frame[i].right=right;
        frame[i].bottom=bottom;
        frame[i].top=top;
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
}
