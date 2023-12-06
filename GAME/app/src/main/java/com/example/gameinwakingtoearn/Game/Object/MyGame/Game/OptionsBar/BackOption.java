package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.OptionsBar;

import android.content.Context;

import com.example.gameinwakingtoearn.R;

public class BackOption extends BasicOption{

    public static final int id = R.drawable.back_button;
    public static final int height = 150;
    public static final int width = 150;
    public BackOption(float x, float y, Context context) {
        super(x, y, context, id, 0, height, width);
        this.setIs_showed(true);
    }


}
