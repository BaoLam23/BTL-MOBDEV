package com.example.gameinwakingtoearn.Game.Object.Running;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.gameinwakingtoearn.Game.Object.MainUI.Authentication;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.FireBaseMangament;
import com.example.gameinwakingtoearn.Game.Object.User.CurrentUser;
import com.example.gameinwakingtoearn.Game.Object.User.User;
import com.example.gameinwakingtoearn.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Chronometer chronometer;
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private boolean isTrackingEnabled = false;
    private Location lastKnownLocation;
    private PolylineOptions polylineOptions;
    private Polyline polyline;
    private TextView timerTextView, caloriesTextView, distanceTextView, stepsTextView;
    private long startTimeMillis;
    private int totalSteps = 0;
    private float totalDistance = 0.0f;
    private long pauseOffset;
    private Marker currentUserMarker; // To store the marker for user's current location

    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if (isTrackingEnabled && locationResult != null && locationResult.getLastLocation() != null) {
                Location location = locationResult.getLastLocation();
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                if (lastKnownLocation != null) {
                    totalDistance += lastKnownLocation.distanceTo(location);
                    drawPolyline(location);
                    totalSteps++;
                }
                lastKnownLocation = location;


                runOnUiThread(() -> updateUI());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

//        timerTextView = findViewById(R.id.timer_text_view);
        caloriesTextView = findViewById(R.id.calories_text_view);
        distanceTextView = findViewById(R.id.distance_text_view);
        stepsTextView = findViewById(R.id.steps_text_view);
        chronometer = findViewById(R.id.chronometer);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, Authentication.class);
                startActivity(intent);
                finish();
            }
        });

//        ToggleButton trackButton = findViewById(R.id.toggle_track_button);
//        trackButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            isTrackingEnabled = isChecked;
//            if (isChecked) {
//                startLocationUpdates();
//            } else {
//                stopLocationUpdates();
//            }
//        });
        ToggleButton trackButton = findViewById(R.id.toggle_track_button);
        trackButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                startLocationUpdates();
            } else {
                stopLocationUpdates();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        lockOnCurrentLocation();
    }

    private void lockOnCurrentLocation() {
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null && mMap != null) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                float DEFAULT_ZOOM = 15;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
                updateMarker(latLng); // Add/update marker on the map
            }
        });
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, getMainLooper());
        if (!isTrackingEnabled) {


            isTrackingEnabled = true;
        }
        chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
        chronometer.start();
    }
    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        if (isTrackingEnabled) {

            isTrackingEnabled = false;
        }
        chronometer.stop();
        pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isTrackingEnabled) {
            stopLocationUpdates();
        }
        User user = CurrentUser.getInstance().getUser();
        int levelUp = totalSteps / 100;
        int level = user.getLevel();
        int exp = user.getCurrentExp();
        double money = totalSteps / 2;
        double moneyCur = user.getMoney();
        user.setLevel(level + levelUp);
        user.setCurrentExp(exp + (totalSteps % 100));
        user.setMoney(moneyCur + money);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isTrackingEnabled) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (CurrentUser.getInstance().getUser() != null) {

            User currentUser = CurrentUser.getInstance().getUser();

            Map<String, Object> userUpdates = new HashMap<>();
            userUpdates.put("email", currentUser.getEmail());
            userUpdates.put("username", currentUser.getUsername());
            userUpdates.put("money", currentUser.getMoney());
            userUpdates.put("totalDistance", currentUser.getTotalDistance());
            userUpdates.put("friendList", currentUser.getFriendList());
            userUpdates.put("buildings", currentUser.getBuildings());
            userUpdates.put("level", currentUser.getLevel());
            userUpdates.put("currentExp", currentUser.getCurrentExp());

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            DocumentReference userDocRef = db.collection("users").document(currentUser.getUid());
            userDocRef.update(userUpdates)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("Firestore", "User data successfully updated on stop!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Firestore", "Error updating user data on stop", e);
                        }
                    });
        }
    }

    private void updateMarker(LatLng latLng) {
        if (currentUserMarker != null) {
            currentUserMarker.setPosition(latLng);
        } else {
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Current Location");
            currentUserMarker = mMap.addMarker(markerOptions);
        }
    }

    private void drawPolyline(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        if (polylineOptions == null) {
            polylineOptions = new PolylineOptions().color(Color.RED).width(5);
        }

        if (polyline != null) {
            polyline.remove(); // Remove previous polyline
        }

        // Add the new location point to the polyline
        polylineOptions.add(latLng);
        polyline = mMap.addPolyline(polylineOptions);
    }
    @SuppressLint("DefaultLocale")
    private void updateUI() {

//        timerTextView.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
//        distanceTextView.setText("Distance: " + String.format("%.2f meters", totalDistance));
        distanceTextView.setText("Distance: " + totalDistance);
        stepsTextView.setText("Steps: " + totalSteps);
        caloriesTextView.setText("Calories burned: " + (totalSteps / 20));

    }


}