package com.example.gameinwakingtoearn.Game.Object.MainUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.gameinwakingtoearn.R;

public class Friends extends AppCompatActivity {

    Button findFriendBtn;
    Button goToFriendReqBtn;
    ImageButton backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        backBtn = findViewById(R.id.backBtnOfFriends);
        findFriendBtn = findViewById(R.id.findFriendBtn);
        goToFriendReqBtn = findViewById(R.id.goToFriendReq);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Authentication.class);
                startActivity(intent);
                finish();
            }
        });

        findFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindFriend.class);
                startActivity(intent);
                finish();
            }
        });

        goToFriendReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PendingRequest.class);
                startActivity(intent);
            }
        });
    }
}