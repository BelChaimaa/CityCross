package com.example.myfirstapplication;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity_Geolocalisation extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    private FusedLocationProviderClient mFusedLocationClient;
    public Marker lastLocationMarker;
    public Marker currentLocationMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_geolocalisation);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


     /*   LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        // Initiate locationCallback
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for (Location location : locationResult.getLocations()) {
                    MapsActivity_Geolocalisation.this.setCurrentLocationMarkerPosition(location);
                }
            }


        };
        // Request the location update
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
*/
    }






    public void getLastPosition() {
        // Tests if permission on location is granted
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation()
                    // Success
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Try to add a marker on the map
                                MapsActivity_Geolocalisation.this.setLastLocationMarkerPosition(location);
                            }
                        }
                    })
                    // Failure
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("MapDemoActivity", "Error trying to get last GPS location");
                            e.printStackTrace();
                        }
                    })
            ;
        } else {
            // Is not...
            Toast.makeText(this, "Erreur: impossible d'accéder à la position", Toast.LENGTH_LONG).show();
        }
    }






    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map=googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);

        LatLng ensg = new LatLng(48.8413002, 2.5868632);
      //  map.addMarker(new MarkerOptions().position(ensg).title("ENSG"));
        // map.moveCamera(CameraUpdateFactory.newLatLngZoom(ensg, 12));

        // Find the last location
        getLastPosition();



    }

    public void setLastLocationMarkerPosition(Location location) {
        // Is map ready?
        if (map != null) {
            LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
            // Need to create Marker and to add it to the map?
            if (lastLocationMarker == null) {
                // Yes
                lastLocationMarker = map.addMarker(new MarkerOptions().position(position).title("Last location"));
            } else {
                // No, just update position
                lastLocationMarker.setPosition(position);
            }
        }
    }



    public void setCurrentLocationMarkerPosition(Location location) {
        // Is map ready?
        if (map != null) {
            LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
            // Need to create Marker and to add it to the map?
            if (currentLocationMarker == null) {
                // Yes
                currentLocationMarker = map.addMarker(new MarkerOptions().position(position).title("Current location"));
            } else {
                // No, just update position
                currentLocationMarker.setPosition(position);
            }
        }
    }


}