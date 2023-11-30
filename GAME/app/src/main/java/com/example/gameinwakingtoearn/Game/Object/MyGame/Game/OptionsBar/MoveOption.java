package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.OptionsBar;


import android.content.Context;
import android.graphics.Canvas;

import com.example.gameinwakingtoearn.R;


public class MoveOption extends BasicOption {
    private BasicOption yesbutton;
    public static final int idYesButton = R.drawable.yes_button;
    private BasicOption nobutton;
    public static final int idNoButton = R.drawable.no_button;
    public static final int height = 50;
    public static final int width = 50;

    public static final int idMoveButton = R.drawable.move_button;
    public MoveOption(float x, float y, Context context) {
        super(x, y, context, idMoveButton , 0,height,width);
        this.is_showed=true;
        yesbutton=new BasicOption(x,y,context, idYesButton,0,height,width);
        nobutton=new BasicOption(x+ yesbutton.getImage().getWidth()+10,y,context,idNoButton,0,height,width);
    }

    @Override
    public void setContext(Context context){
        yesbutton.setContext(context);
        nobutton.setContext(context);
        super.setContext(context);
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
        nobutton.update(x + yesbutton.getImage().getWidth() + 10, y);
    }


}

