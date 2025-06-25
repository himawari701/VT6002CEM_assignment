package com.example.vt6002cem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.net.Uri;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


import java.util.List;

public class MapActivity extends AppCompatActivity {
    TextView tv_lat, tv_lon, tv_altitude, tv_accuracy, tv_speed, tv_sensor, tv_updates, tv_address;
    private static final int PERMISSIONS_FINE_LOCATION=99;
    Switch sw_locationsupdates, sw_gps;
    Button btn_googleMap;
    boolean updateOn = false;
    LocationRequest locationRequest;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        tv_lat=findViewById(R.id.tv_lat);
        tv_lon=findViewById(R.id.tv_lon);
        tv_altitude=findViewById(R.id.tv_altitude);
        tv_accuracy=findViewById(R.id.tv_accuracy);
        tv_speed=findViewById(R.id.tv_speed);
        tv_sensor=findViewById(R.id.tv_sensor);
        tv_updates=findViewById(R.id.tv_updates);
        tv_address=findViewById(R.id.tv_address);
        sw_gps=findViewById(R.id.sw_gps);
        sw_locationsupdates=findViewById(R.id.sw_locationsupdates);
        btn_googleMap = findViewById(R.id.btn_googleMap);

        locationRequest = new LocationRequest();
        locationRequest.setInterval(30000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        sw_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sw_gps.isChecked()){
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    tv_sensor.setText("Using GPS sensors");

                }else{
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    tv_sensor.setText("Using Towers+WIFI");
                }

            }

        });
        updateGPS();

        btn_googleMap.setOnClickListener(v -> {
            String latitude = tv_lat.getText().toString();
            String longitude = tv_lon.getText().toString();

            Intent it = new Intent(Intent.ACTION_VIEW);
            //set zoom level and query for restaurants
            it.setData(Uri.parse("geo:" + latitude + "," + longitude + "?z=18&q="));
            startActivity(it);
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSIONS_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    updateGPS();
                }else{
                    Toast.makeText(this, "This app requires permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }


    }

    private void updateGPS(){

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MapActivity.this);
        if (ActivityCompat.checkSelfPermission(MapActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    updateUIValues(location);
                }
            });
        }else{

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_FINE_LOCATION);
            }
        }
    }
    private void updateUIValues(Location location){
        tv_lat.setText(String.valueOf(location.getLatitude()));
        tv_lon.setText(String.valueOf(location.getLongitude()));
        tv_accuracy.setText(String.valueOf(location.getAccuracy()));

        if (location.hasAltitude()){
            tv_altitude.setText(String.valueOf(location.getAltitude()));
        }else{
            tv_altitude.setText("Not available");}

        if (location.hasSpeed()){
            tv_speed.setText(String.valueOf(location.getSpeed()));
        }else{
            tv_speed.setText("Not available");}

        Geocoder geocoder =new Geocoder(MapActivity.this);

        try {
            List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(),1);
            tv_address.setText(addresses.get(0).getAddressLine(0));
        }
        catch (Exception e)

        {
            tv_address.setText("Unable to get street address");
        }
    }
    private void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


}