package com.example.myfirstapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import net.sf.geographiclib.*; // librairie externe qui permet d'effectuer les calculs géodésiques comme le problème inverse, situé dans app/libs

public class guidage extends AppCompatActivity implements Orientation.Listener {

    final int red = Color.parseColor("#F44336");
    final int green = Color.parseColor("#4CAF50");

    LocationManager locationManager;

    private ConstraintLayout initLayout;
    private ProgressBar progressBar;
    private Button btnVille;
    private TextInputEditText inputVille;
    private TextView txtDirection;
    private TextView txtAz1;
    private TextView txtS12;

    private Orientation mOrientation;
    private boolean corrige = false;

    private double[] coordsNordMagnetique = {81.08, -73.13};

    private GeodesicData inverseUtilisateurNordMagnetique;
    private GeodesicData inverseUtilisateurVille;

    private double[] coordsUtilisateur;
    private double direction;
    /* Attributs temporaires utilisés pour les tests en attendant leur implémentation dans les classes dédiées */
    private double[] coordsVille = {21.422510, 39.826168};
    private double distance;
    private double seuilAngle = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidage);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(guidage.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListenerGPS);
        }

        initLayout = findViewById(R.id.initLayout);
        progressBar = findViewById(R.id.progressBar);
        btnVille = findViewById(R.id.btnVille);
        inputVille = findViewById(R.id.inputVille);
        txtDirection = findViewById(R.id.txtDirection);
        txtAz1 = findViewById(R.id.txtAz1);
        txtS12 = findViewById(R.id.txtS12);

        mOrientation = new Orientation(this);

        btnVille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Log.d("input", inputVille.getText().toString());
                    inverseUtilisateurVille = Geodesic.WGS84.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsVille[0], coordsVille[1]);
                    inverseUtilisateurNordMagnetique = Geodesic.WGS84.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsNordMagnetique[0], coordsNordMagnetique[1]);
                    corrige = true;
                    txtAz1.setText(inverseUtilisateurVille.azi1 + " ");
                    txtS12.setText(inverseUtilisateurVille.s12 + " ");
                } catch (NullPointerException e) {
                    Log.d("Error", String.valueOf(e)); // l'appareil n'a pas encore été géolocalisé
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mOrientation.startListening(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mOrientation.stopListening();
    }


    @Override
    public void onOrientationChanged(double azimuth) {
        if(corrige){
            direction = azimuth + inverseUtilisateurNordMagnetique.azi1;
            txtDirection.setText(Double.toString(direction));
            if(Math.abs(direction - inverseUtilisateurVille.azi1) < seuilAngle){
                initLayout.setBackgroundColor(green);
            } else {
                initLayout.setBackgroundColor(red);
            }
        } else {
            txtDirection.setText(Double.toString(azimuth));
        }
    }

    LocationListener locationListenerGPS=new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("display location", "Latitude: "+location.getLatitude()+", longitude: "+location.getLongitude());
            coordsUtilisateur = new double[]{location.getLatitude(), location.getLongitude()};
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("display location", "location = null");
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(guidage.this);

            alertDialog.setTitle("GPS");
            alertDialog.setMessage("Votre GPS n'est pas activé, l'application ne peut pas fonctionner sans votre localisation.");
            alertDialog.setPositiveButton("Paramêtres", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    guidage.this.startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(guidage.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            alertDialog.show();
        }
    };
}