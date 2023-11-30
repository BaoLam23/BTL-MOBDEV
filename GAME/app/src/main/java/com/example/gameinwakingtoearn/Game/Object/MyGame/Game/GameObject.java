package com.example.gameinwakingtoearn.Game.Object.MyGame.Game;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class GameObject {
    protected float x=0;
    protected float y=0;

    protected final Sprite image;
    protected boolean is_clicked=false;


    private final int id;

    public GameObject(float x, float y, Context context, int id, int quatities_frame, int height,int width){
        this.x=x;
        this.y=y;


        this.image=new Sprite(context,id,quatities_frame,height,width);

        image.setPos((int) this.x, (int) this.y, (int) this.x+width, (int) this.y+height);

        this.id = id;

    }

    public void setContext(Context context){

        this.image.setContext(context);
    }
    public Context getContext(){ return  this.image.getContext() ;}

    public int getId(){ return this.id;}


    public  void draw(Canvas canvas){

        canvas.drawBitmap(this.image.getBitmap(),null,this.image.getPos(),null);
    };
    public void check_is_clicked(float x,float y){
        try {
            if (x >= this.image.getPos().left && x <= this.image.getPos().right &&
                    y >= this.image.getPos().top && y <= this.image.getPos().bottom) {
                this.is_clicked = true;
            } else {
                this.is_clicked = false;
            }
        } catch (Exception e){
            Log.e("Game object set is clicked is","error");
        }
    }
    public void update(){
        this.image.setPos((int) this.x, (int) this.y, (int) (this.x+ image.getWidth()), (int) (this.y+ image.getHeight()));

    }
    public void update(int x,int y){
        this.image.setPos((int) (x), (int) (y), (int) (x+ getPos().width()), (int) (y+ getPos().height()));
        this.x=x;
        this.y=y;

    }
    public boolean get_is_clicked(){
        return this.is_clicked;
    }
    public void set_is_clicked(boolean b){

        this.is_clicked=b;

    }
    public Rect getPos(){
        return this.image.getPos();
    }
    public float getPosX(){
        return this.image.getPos().left;
    }
    public float getPosY(){
        return this.image.getPos().top;
    }
    public void setPos(float x,float y){
        if(is_clicked) {
            this.x=x;
            this.y=y;
        }

    }
    public Rect getFrame(int i){
        return this.image.getFrame(i);
    }

    public Sprite getImage(){
        return this.image;
    }


}

