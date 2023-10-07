package com.example.testmap.MyWalking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testmap.R;

public class WalkingActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager = null;
    private Sensor stepSensor;
    private int totalSteps = 0;
    private int previewTotalSteps = 0;
    private ProgressBar progressBar;
    private TextView steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking);

        progressBar = findViewById(R.id.progressBar);
        steps = findViewById(R.id.steps);

        resetSteps();
//        loadData();

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 1);
        }

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        assert sensorManager != null;
//        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (stepSensor == null) {
            Toast.makeText(this, "This device has no sensor in onCreate", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Not null", Toast.LENGTH_SHORT).show();
        }
//        sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        if (sensorManager != null) {
//            Log.d("sensorManager", "onResume: not null");
//        }
//
//        if (stepSensor == null) {
//            Toast.makeText(this, "This device has no sensor", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "Yeaaaa", Toast.LENGTH_LONG).show();

            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            totalSteps = (int) sensorEvent.values[0];
            int currentSteps = totalSteps - previewTotalSteps;
            steps.setText(String.valueOf(currentSteps));

            progressBar.setProgress(currentSteps);
        }
    }

    private void resetSteps() {
        steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WalkingActivity.this, "Long press to reset steps", Toast.LENGTH_SHORT).show();
            }
        });

        steps.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                previewTotalSteps = totalSteps;
                steps.setText("0");
                progressBar.setProgress(0);
                saveData();
                return true;
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key1", String.valueOf(previewTotalSteps));
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        int savedNumber = (int) sharedPreferences.getFloat("key1", 0f);
        previewTotalSteps = savedNumber;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}