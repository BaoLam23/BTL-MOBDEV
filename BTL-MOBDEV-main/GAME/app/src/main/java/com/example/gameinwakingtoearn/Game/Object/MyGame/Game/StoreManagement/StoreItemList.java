package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.Game;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.MyListManagement;

import java.util.ArrayList;

public class StoreItemList extends MyListManagement {
    private Paint paint;
    private static final String StoreName="Cửa Hàng";

    // chứa danh sách của từng item nhà một
    public StoreItemList(Context context, float x, float y, MyBag bag, ArrayList<Structure> city, ArrayList<ArrayList<Integer>> area ,
                         MyStore myStore) {
        super(context, x, y,5,4,3,5,
                50,200, Game.getScreenWidth()-50,Game.getScreenHeight()-200);
        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);

        //cập nhập các item trong của hàng
        for(int i=0;i<5;i++) {
            this.addNewItem(new ItemHouseInStore(this.background.left, this.background.top, context,bag,city,area,myStore),0);
        }

    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawText(StoreName,(this.background.right-this.background.left)/2-50,
                this.background.top+paint.getTextSize(),paint);
    }

    public Rect getBackground(){
        return this.background;
    }

   
}
