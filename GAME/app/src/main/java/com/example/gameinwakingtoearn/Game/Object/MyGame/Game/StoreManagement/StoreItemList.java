package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemDirt1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.Game;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.MyListManagement;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.Sprite;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class StoreItemList extends MyListManagement {
    private Paint paint;

    private Sprite moneyBar;
    public static  final int heightMoneyBar = 100;
    public static  final int widthMoneyBar = 300;
    private static final String StoreName="Cửa Hàng";

    public static final int id = R.drawable.backdrop;
    public static final  int idMoney = R.drawable.balance;

    // chứa danh sách của từng item nhà một
    public StoreItemList(Context context, float x, float y, MyBag bag, ArrayList<Structure> city, ArrayList<Structure> dirt ,
                         MyStore myStore) {
        super(context, x, y,5,3,3,5,
                id,50,200, Game.getScreenWidth()-50,Game.getScreenHeight()-200);
        paint=new Paint();
        paint.setColor(Color.WHITE);
//        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "karantina_regular.ttf");
//        paint.setTypeface(typeface);
        paint.setTextSize(40);
        this.moneyBar = new Sprite(context,idMoney,0,heightMoneyBar,widthMoneyBar);
        this.moneyBar.setPos(this.background.getPos().left,
                this.background.getPos().top + (int)paint.getTextSize() + 10,
                this.background.getPos().left + widthMoneyBar,
                this.background.getPos().top + (int)paint.getTextSize() + 10 + heightMoneyBar);

        //cập nhập các item trong của hàng


        this.addNewItem(new ItemHouse1InStore(this.background.getPos().left, this.background.getPos().top, context,bag,city,dirt,myStore),0);

        this.addNewItem(new ItemHouse2InStore(this.background.getPos().left, this.background.getPos().top, context,bag,city,dirt,myStore),0);

        this.addNewItem(new ItemHouse3InStore(this.background.getPos().left, this.background.getPos().top, context,bag,city,dirt,myStore),0);

        this.addNewItem(new ItemTree1InStore(this.background.getPos().left, this.background.getPos().top, context,bag,city,dirt,myStore),0);

        this.addNewItem(new ItemTree2InStore(this.background.getPos().left, this.background.getPos().top, context,bag,city,dirt,myStore),0);

        this.addNewItem(new ItemTree3InStore(this.background.getPos().left, this.background.getPos().top, context,bag,city,dirt,myStore),0);

        this.addNewItem(new ItemTree4InStore(this.background.getPos().left, this.background.getPos().top, context,bag,city,dirt,myStore),0);

        this.addNewItem(new ItemDirt1InStore(this.background.getPos().left, this.background.getPos().top, context,bag,city,dirt,myStore),0);


    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawText(StoreName,(this.background.getPos().right-this.background.getPos().left)/2-50,
                this.background.getPos().top+paint.getTextSize(),paint);
        this.moneyBar.draw(canvas);
    }

    public Sprite getBackground(){
        return this.background;
    }
    public Sprite getMoneyBar(){
        return this.moneyBar;
    }

   
}
