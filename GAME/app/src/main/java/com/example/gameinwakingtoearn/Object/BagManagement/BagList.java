package com.example.gameinwakingtoearn.Object.BagManagement;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.gameinwakingtoearn.Game;
import com.example.gameinwakingtoearn.Object.MyDesignList.MyListManagement;

public class BagList extends MyListManagement {
    private final String label="túi đồ";
    private Paint paint;
    public BagList(Context context, float x, float y) {
        super(context, x, y, 2, 50, 4, 3,
                50,100, Game.getScreenWidth()-50,Game.getScreenHeight()-100);
        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
    }
    @Override
    public void draw(Canvas canvas){
         super.draw(canvas);
         canvas.drawText(this.label,(this.background.left+this.background.right)/2-50,
                 this.background.top+paint.getTextSize(),paint);
    }
}
