package com.example.gameinwakingtoearn.Game.Object.MainUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.FriendsCityUI;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.FriendsFireBase;
import com.example.gameinwakingtoearn.R;

public class LoadingFriendCity extends AppCompatActivity implements visitFriendsCityListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_friend_city);
        Intent intent = getIntent();
        FriendsFireBase.fetchUserData(intent.getStringExtra(Friends.KEY_FRIENDS_ID),this);
    }

    @Override
    public void moveIntoFriendCity() {
        Intent intent = new Intent(LoadingFriendCity.this, FriendsCityUI.class);
        startActivity(intent);
        finish();
    }
}