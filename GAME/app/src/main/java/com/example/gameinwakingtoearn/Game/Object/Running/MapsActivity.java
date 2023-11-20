package com.example.gameinwakingtoearn.Game.Object.Running;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.FragmentActivity;

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

import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

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
                }
                lastKnownLocation = location;

                totalSteps++;
                runOnUiThread(() -> updateUI());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        timerTextView = findViewById(R.id.timer_text_view);
        caloriesTextView = findViewById(R.id.calories_text_view);
        distanceTextView = findViewById(R.id.distance_text_view);
        stepsTextView = findViewById(R.id.steps_text_view);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, GPS.class);
                startActivity(intent);
            }
        });

        ToggleButton trackButton = findViewById(R.id.toggle_track_button);
        trackButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isTrackingEnabled = isChecked;
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
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isTrackingEnabled) {
            startLocationUpdates();
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
    private void updateUI() {
        long currentTimeMillis = System.currentTimeMillis();
        long elapsedTimeMillis = currentTimeMillis - startTimeMillis;
        int seconds = (int) (elapsedTimeMillis / 1000);
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds %= 60;

        timerTextView.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds));
        distanceTextView.setText(String.format(Locale.getDefault(), "%.2f meters", totalDistance));
        stepsTextView.setText(String.valueOf(totalSteps));
        caloriesTextView.setText("Calories burned: " + (totalSteps / 20));
    }
}
