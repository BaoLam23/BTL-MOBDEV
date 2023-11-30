package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.OptionsBar;


import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;


public class OptionsBar {
    private MoveOption moveOption;
    private SellOption sellOption;
    private  MoveToInventoryOption moveToInventoryOption;
    public OptionsBar(float x, float y, Context context){

        moveOption=new MoveOption(x,y,context);
        moveToInventoryOption = new MoveToInventoryOption(x + MoveOption.width,y,context);
        sellOption = new SellOption(x + MoveOption.width + MoveToInventoryOption.width,y,context);
        sellOption.setIs_showed(true);
        moveToInventoryOption.setIs_showed(true);

    }
    public void draw(Canvas canvas){

        moveOption.draw(canvas);

        if(!moveOption.get_is_clicked() && moveOption.is_showed) {
            sellOption.draw(canvas);
            moveToInventoryOption.draw(canvas);
        }

    }


    public void check_is_clicked(float x, float y) {
             moveOption.check_is_clicked(x,y);

             if(!moveOption.get_is_clicked() && moveOption.is_showed) {
                 sellOption.check_is_clicked(x, y);
                 moveToInventoryOption.check_is_clicked(x, y);
             }

    }


    public BasicOption getMoveOption(){
        return moveOption;
    }
    public BasicOption getNoButtonOfMoveOption(){
        return this.moveOption.getNobutton();
    }
    public BasicOption getYesButtonOfMoveOption(){
        return this.moveOption.getYesbutton();
    }

    public  BasicOption getMoveToInventoryOption(){
        return this.moveToInventoryOption;
    }

    public  BasicOption getSellOption(){
        return this.sellOption;
    }


    public void update(int x,int y){




        int posXOfMoveOption = x;
        int posYOfMoveOption = y;

        int posXOfMoveToInventoryOption = x + MoveOption.width;
        int posYOfMoveToInventoryOption = y;

        int posXOfSellOption = x + MoveOption.width + MoveToInventoryOption.width;
        int posYOfSellOption = y;

        moveOption.update(posXOfMoveOption,posYOfMoveOption);
        sellOption.update(posXOfSellOption,posYOfSellOption);
        moveToInventoryOption.update(posXOfMoveToInventoryOption,posYOfMoveToInventoryOption);
    }

    public void setContext(Context context){
        moveOption.setContext(context);
        sellOption.setContext(context);
        moveToInventoryOption.setContext(context);
    }


}

