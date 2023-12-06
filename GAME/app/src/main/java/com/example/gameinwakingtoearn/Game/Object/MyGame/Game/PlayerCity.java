package com.example.gameinwakingtoearn.Game.Object.MyGame.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.gameinwakingtoearn.R;

public class PlayerCity extends GameUI {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.game = new Game(this,-1,Game.host,this);
        setContentView(game);


    }

}