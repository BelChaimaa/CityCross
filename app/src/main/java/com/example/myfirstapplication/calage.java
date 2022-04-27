package com.example.myfirstapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class calage extends FragmentActivity implements OnMapReadyCallback{

    GoogleMap map;
    private Orientation mOrientation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calage);

        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mOrientation = new Orientation(this);
     //   mGeolocalisation=new Geolocalisation(this);


    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map=googleMap;

        LatLng paris =new LatLng(48.886478, 2.305111);
        map.addMarker(new MarkerOptions().position(paris).title("paris"));
        map.moveCamera(CameraUpdateFactory.newLatLng(paris));


    }
}