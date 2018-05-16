package com.example.arjun_mu.android;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity implements FetchAddressTask.OnTaskCompleted {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    TextView mLocationTextView;
    private ImageView mAndroidImageView;
    boolean mTrackingLocation;

    FusedLocationProviderClient mFusedLocationClient;
    Location mLastLocation;
    LocationCallback mLocationCallback;
    // Animation
    private AnimatorSet mRotateAnim;
    Button mButton;
    private static final String TRACKING_LOCATION_KEY = "tracking_location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationTextView = (TextView) findViewById(R.id.location);
        mButton=(Button)findViewById(R.id.button3) ;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mAndroidImageView = (ImageView) findViewById(R.id.imageview_android);

        mRotateAnim = (AnimatorSet) AnimatorInflater.loadAnimator
                (this, R.animator.rotate);

        mRotateAnim.setTarget(mAndroidImageView);



        // Initialize the location callbacks.
        mLocationCallback = new LocationCallback() {
            /**
             * This is the callback that is triggered when the
             * FusedLocationClient updates your location.
             * @param locationResult The result containing the device location.
             */
            @Override
            public void onLocationResult(LocationResult locationResult) {
                // If tracking is turned on, reverse geocode into an address
                if (mTrackingLocation) {
                    new FetchAddressTask(MainActivity.this, MainActivity.this)
                            .execute(locationResult.getLastLocation());
                }
            }
        };

        if (savedInstanceState != null) {
            mTrackingLocation = savedInstanceState.getBoolean(
                    TRACKING_LOCATION_KEY);
        }
    }

    private void startTrackingLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            Log.d(TAG, "getLocation: permissions granted");


            mFusedLocationClient.requestLocationUpdates
                    (getLocationRequest(),
                            mLocationCallback,
                            null /* Looper */);



            mRotateAnim.start();
            mTrackingLocation=true;
            mButton.setText("Stop Tracking Location");
        }
    }

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    private  void stopTrackingLocation(){
        if (mTrackingLocation) {
            mTrackingLocation = false;
            mButton.setText(R.string.start_tracking_location);
            mLocationTextView.setText(R.string.textview_hint);
            mRotateAnim.end();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                // If the permission is granted, get the location,
                // otherwise, show a Toast
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startTrackingLocation();
                } else {
                    Toast.makeText(this,
                            R.string.location_permission_denied,
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void getlocationofuser(View view) {

        if (!mTrackingLocation) {
            startTrackingLocation();
        } else {
            stopTrackingLocation();
        }

    }

    @Override
    public void onTaskCompleted(String result) {
        if (mTrackingLocation) {
            // Update the UI
            mLocationTextView.setText(getString(R.string.address_text,
                    result, System.currentTimeMillis()));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putBoolean(TRACKING_LOCATION_KEY, mTrackingLocation);
        super.onSaveInstanceState(outState, outPersistentState);
    }
    @Override
    protected void onPause() {
        if (mTrackingLocation) {
            stopTrackingLocation();
            mTrackingLocation = true;
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mTrackingLocation) {
            startTrackingLocation();
        }
        super.onResume();
    }

}
