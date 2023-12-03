package com.example.gameinwakingtoearn.Game.Object.MainUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gameinwakingtoearn.R;

public class Friends extends AppCompatActivity {

    Button findFriendBtn;
    Button backBtn, goToFriendReqBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        backBtn = findViewById(R.id.backBtn);
        findFriendBtn = findViewById(R.id.findFriendBtn);
        goToFriendReqBtn = findViewById(R.id.goToFriendReq);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Authentication.class);
                startActivity(intent);
            }
        });

        findFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindFriend.class);
                startActivity(intent);
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