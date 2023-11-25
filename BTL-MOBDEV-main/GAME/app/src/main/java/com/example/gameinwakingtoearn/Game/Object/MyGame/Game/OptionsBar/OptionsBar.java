package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.OptionsBar;


import android.content.Context;
import android.graphics.Canvas;


public class OptionsBar {
    private MoveOption moveOption;
    public OptionsBar(float x, float y, Context context){

        moveOption=new MoveOption(x,y,context);
    }
    public void draw(Canvas canvas){
        moveOption.draw(canvas);
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

    public void update(int x,int y){
        moveOption.update(x,y);
    }


}

