package com.example.gameinwakingtoearn.Game.Object.MainUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.gameinwakingtoearn.R;

public class LoadingActivity extends AppCompatActivity {

    private boolean isLoad = true;

    public void changeIntoAuthetication(){

       this.onResume();
    }

    public void setLoad(boolean b){
        this.isLoad = b;
    }
    public boolean getLoad(){
        return  this.isLoad;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
    }


    @Override
    protected void onResume(){
        super.onResume();
        Log.e("check is load",isLoad + " ");
        if(!isLoad){
            Intent intent = new Intent(this,Authentication.class);
            startActivity(intent);
            finish();
        }

    }
}