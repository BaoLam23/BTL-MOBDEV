package com.example.testmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testmap.MyWalking.WalkingActivity;

public class MainActivity extends AppCompatActivity {

    Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGame();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWalking();
            }
        });
    }

    public void goToGame() {
        Intent intent = new Intent(this, com.example.testmap.MyGame.MainActivity.class);
        startActivity(intent);
    }

    public void goToWalking() {
        Intent intent = new Intent(this, WalkingActivity.class);
        startActivity(intent);
    }
}