package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.OptionsBar;


import android.content.Context;
import android.graphics.Canvas;


import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;

public class BasicOption extends GameObject {
    protected boolean is_showed=false;
    public static  final int height = 100;
    public static  final int width = 100;

    public BasicOption(float x, float y, Context context, int id, int quatities_frame,int zoom){
        super(x,y,context,id,quatities_frame,height,width);

    }
    public void setIs_showed(boolean b){
        this.is_showed=b;
    }
    public boolean getIs_showed(){
        return this.is_showed;
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image.getBitmap(),null,
                this.image.getPos(),null);
    }

    @Override
    public void check_is_clicked(float x, float y) {
        if(x<=this.image.getPos().right && x>=this.image.getPos().left
                && y<=this.image.getPos().bottom && y>=this.image.getPos().top && is_showed){
            this.is_showed=false;
            set_is_clicked(true);

        }

    }


    @Override
    public void update() {

    }


}
