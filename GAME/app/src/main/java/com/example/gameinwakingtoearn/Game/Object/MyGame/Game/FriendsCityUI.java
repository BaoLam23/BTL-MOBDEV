package com.example.gameinwakingtoearn.Game.Object.MyGame.Game;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.gameinwakingtoearn.R;

public class FriendsCityUI extends GameUI {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.game = new FriendCity(this,-1,Game.clients,this);
        setContentView(game);


    }


    @Override
    public void onGameFinished() {
        finish();
    }
}