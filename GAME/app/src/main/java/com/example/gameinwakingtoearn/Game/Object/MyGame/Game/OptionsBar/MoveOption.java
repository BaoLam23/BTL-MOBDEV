package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.OptionsBar;


import android.content.Context;
import android.graphics.Canvas;

import com.example.gameinwakingtoearn.R;


public class MoveOption extends BasicOption {
    private BasicOption yesbutton;
    private BasicOption nobutton;
    public MoveOption(float x, float y, Context context) {
        super(x, y, context, R.drawable.movesign , 0,-30);
        this.is_showed=true;
        yesbutton=new BasicOption(x,y,context, R.drawable.yessign,0,-30);
        nobutton=new BasicOption(x+ yesbutton.getWidth()+10,y,context,R.drawable.nosign,0,-30);
    }
    public BasicOption getYesbutton(){return yesbutton;}
    public BasicOption getNobutton(){return nobutton;}

    public void draw(Canvas canvas){
        if(!this.get_is_clicked()) {
            super.draw(canvas);
        }
        else{
            yesbutton.setIs_showed(true);
            nobutton.setIs_showed(true);
            yesbutton.draw(canvas);
            nobutton.draw(canvas);
        }
    }



    public void update(int x,int y) {
        super.update(x,y);
        yesbutton.update(x, y);
        nobutton.update(x + yesbutton.getWidth() + 10, y);
    }


}

