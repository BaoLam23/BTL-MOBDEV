package com.example.gameinwakingtoearn.Game.Object.MainUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.FireBaseMangament;
import com.example.gameinwakingtoearn.R;

public class AdjustInformationLayout extends AppCompatActivity {
    private ImageButton backButton;
    private EditText changeName;
    private  EditText changePassWord;

    private ImageButton confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_information_layout);
        backButton = findViewById(R.id.backButtonInChangeImformation);

        changeName= findViewById(R.id.changeName);
        changePassWord= findViewById(R.id.changePassword);

        confirmButton = findViewById(R.id.saveInformationButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdjustInformationLayout.this,Authentication.class);
                startActivity(intent);
                finish();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!changeName.getText().toString().isEmpty()){
                   FireBaseMangament.changeNameUser(changeName.getText().toString());
                   Toast.makeText(AdjustInformationLayout.this,"change name successful",Toast.LENGTH_SHORT).show();
               }

                if(!changePassWord.getText().toString().isEmpty()){
                    FireBaseMangament.changePassword(changePassWord.getText().toString());
                    Toast.makeText(AdjustInformationLayout.this,"change password successful",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}