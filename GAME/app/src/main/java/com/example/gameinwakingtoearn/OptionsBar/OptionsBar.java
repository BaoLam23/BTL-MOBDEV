package com.example.gameinwakingtoearn.OptionsBar;


import android.content.Context;
import android.graphics.Canvas;


public class OptionsBar {
    private MoveOption moveOption;
    public OptionsBar(float x, float y, Context context){

        moveOption=new MoveOption(x,y,context);
    }
    public void draw(Canvas canvas){
        if(!moveOption.get_is_clicked()) {
            moveOption.draw(canvas);
        }
        else{
            this.getYesButtonOfMoveOption().setIs_showed(true);
            this.getNoButtonOfMoveOption().setIs_showed(true);
            moveOption.getYesbutton().draw(canvas);
            moveOption.getNobutton().draw(canvas);
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
    public void update(int x,int y){
        moveOption.update(x,y);
        moveOption.getYesbutton().update(x,y);
        moveOption.getNobutton().update(x+moveOption.getYesbutton().getWidth()+10,y);

    }

}

