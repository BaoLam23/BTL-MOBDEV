package com.example.testmap.MyGame;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.testmap.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    public static final int DEFAULT_UPDATE_INTERVAL = 15;
    public static final int FAST_UPDATE_INTERVAL = 3;
    private static final int PERMISSION_FINE_LOCATION = 99;

    //references to the UI elements
    TextView tv_lat, tv_lon, tv_altitude, tv_accuracy, tv_speed, tv_sensor, tv_updates, tv_address;

    Switch sw_locationupdates, sw_gps;

    // variable to see if we are tracking location or not.
    boolean updateOn = false;

    //Location request is a config file for setting related to FusedLocationProviderClient
    LocationRequest locationRequest;
    LocationCallback locationCallBack;

    //Google API for location service.
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps_starting);

        // give UI variable value

        tv_lat = findViewById(R.id.tv_lat);
        tv_lon = findViewById(R.id.tv_lon);
        tv_altitude = findViewById(R.id.tv_altitude);
        tv_accuracy = findViewById(R.id.tv_accuracy);
        tv_speed = findViewById(R.id.tv_speed);
        tv_sensor = findViewById(R.id.tv_sensor);
        tv_updates = findViewById(R.id.tv_updates);
        tv_address = findViewById(R.id.tv_address);
        sw_gps = findViewById(R.id.sw_gps);
        sw_locationupdates = findViewById(R.id.sw_locationsupdates);

        //set all properties of LocationRequest

        locationRequest = new LocationRequest();

        //how often default location check happen ?
        locationRequest.setInterval(1000 * DEFAULT_UPDATE_INTERVAL);

        //how often fast mode location check happen ?
        locationRequest.setInterval(1000 * FAST_UPDATE_INTERVAL);

        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        //event that triggered whenever the update interval is met
        locationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                // save the location
                Location location = locationResult.getLastLocation();
                updateUIValues(location);
            }
        };

        sw_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sw_gps.isChecked()) {
                    //most accurate - use GPS
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    tv_sensor.setText("Using GPS sensor");
                } else {
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    tv_sensor.setText("Using Towers + WIFI");
                }
            }
        });

        sw_locationupdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sw_locationupdates.isChecked()){
                    // turn on location tracking
                    startLocationUpdates();
                }
                else {
                    // turn off tracking
                    stopLocationUpdates();
                }
            }
        });

        updateGPS();
    } // end onCreate

    private void  startLocationUpdates(){
        tv_updates.setText("Tracking now");
    fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallBack, null);

    }

    private void   stopLocationUpdates(){
        tv_updates.setText("Not tracking");
        tv_lat.setText("Not tracking");
        tv_lon.setText("Not tracking");
        tv_speed.setText("Not tracking");
        tv_address.setText("Not tracking");
        tv_accuracy.setText("Not tracking");
        tv_altitude.setText("Not tracking");
        tv_sensor.setText("Not tracking");

        fusedLocationProviderClient.removeLocationUpdates(locationCallBack);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_FINE_LOCATION:;
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateGPS();
                }
                else{
                    Toast.makeText(this,"This app requires permission to work ",Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    private void updateGPS(){
        // get permissions from user to track GPS
        // get current location from the fused client
        // update the UI

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //user provide permission
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                        //we got permissions.Put values of location into UI components.
                        updateUIValues(location);
                }
            });
        }
        else {
            //permissions not granted

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_FINE_LOCATION);
            }
        }

        }

    //update all text view object
    private void updateUIValues(Location location){
        tv_lat.setText(String.valueOf(location.getLatitude()));
        tv_lon.setText(String.valueOf(location.getLongitude()));
        tv_accuracy.setText(String.valueOf(location.getAccuracy()));

        if(location.hasAltitude()){
            tv_altitude.setText(String.valueOf(location.getAltitude()));
        }
        else {
            tv_altitude.setText("NOT AVAILABLE");
        }

        if(location.hasAltitude()){
            tv_speed.setText(String.valueOf(location.getAltitude()));
        }
        else {
            tv_speed.setText("NOT AVAILABLE");
        }
    }
}