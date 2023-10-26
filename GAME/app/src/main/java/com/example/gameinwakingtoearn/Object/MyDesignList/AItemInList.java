package com.example.gameinwakingtoearn.Object.MyDesignList;

import android.content.Context;
import android.graphics.Canvas;

import com.example.gameinwakingtoearn.Object.GameObject;

public class AItemInList extends GameObject {
    public AItemInList(float x, float y, Context context, int id, int zoom) {
        super(x, y, context, id, 0, zoom);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.getImage().getBitmap(),null,this.getImage().getPos(),null);
    }

    @Override
    public void check_is_clicked(float x, float y) {
        if(x >= this.image.getPos().left && x <= this.image.getPos().right &&
                y<= this.image.getPos().bottom && y >= this.image.getPos().top){
            this.is_clicked=true;
        }
    }

    @Override
    public void update() {

    }
}
