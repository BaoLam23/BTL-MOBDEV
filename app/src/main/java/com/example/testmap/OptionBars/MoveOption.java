package com.example.testmap.OptionBars;

import android.content.Context;
import android.graphics.Canvas;

import com.example.testmap.R;

public class MoveOption extends BasicOption {
    private BasicOption yesbutton;
    private BasicOption nobutton;
    public MoveOption(float x, float y, Context context) {
        super(x, y, context, R.drawable.pixil_frame_0 , 0,-50);
        this.is_showed=true;
        yesbutton=new BasicOption(x,y,context,R.drawable.tick,0,-20);
       nobutton=new BasicOption(x+ yesbutton.getWidth()+10,y,context,R.drawable.no_button,0,-20);
    }
    public BasicOption getYesbutton(){return yesbutton;}
    public BasicOption getNobutton(){return nobutton;}


}
