package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.OptionsBar;

import android.content.Context;

import com.example.gameinwakingtoearn.R;

public class MoveToInventoryOption extends BasicOption{

    public static final int id = R.drawable.move_to_inventory_button;
    public static final int height = 50;
    public static final int width = 50;
    public MoveToInventoryOption(float x, float y, Context context) {
        super(x, y, context, id, 0, height, width);
    }
}
