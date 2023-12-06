package com.example.gameinwakingtoearn.Game.Object.MainUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.gameinwakingtoearn.Game.Object.User.CurrentUser;
import com.example.gameinwakingtoearn.R;
import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {
    private ImageButton backButton;
    private ImageButton logOutButton;

    private ImageButton changeInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        changeInformation = findViewById(R.id.moveToChangeInformationButton);

        changeInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this,AdjustInformationLayout.class);
                startActivity(intent);
                finish();
            }
        });



        backButton = (ImageButton) findViewById(R.id.backButtonSettings);
        logOutButton = (ImageButton) findViewById(R.id.logout);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this,Authentication.class);
                startActivity(intent);
                finish();
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                CurrentUser.getInstance().setUser(null);
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

}