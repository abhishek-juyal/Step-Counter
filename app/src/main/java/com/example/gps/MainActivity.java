package com.example.gps;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class MainActivity extends Activity implements LocationListener{

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected ContextCompat contextCompat;
    protected PackageManager packageManager;
    Build build;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude,longitude;
    protected boolean gps_enabled,network_enabled;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            txtLat = (TextView) findViewById(R.id.textview1);

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if ( Build.VERSION.SDK_INT >= 23 &&
                    ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return  ;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0, locationListener);

        }
        @Override
        public void onLocationChanged(Location location) {
            txtLat = (TextView) findViewById(R.id.textview1);
            txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }
    }