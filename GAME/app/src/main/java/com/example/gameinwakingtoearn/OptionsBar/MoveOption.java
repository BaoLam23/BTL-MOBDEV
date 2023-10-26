package com.example.gameinwakingtoearn.OptionsBar;


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


}

