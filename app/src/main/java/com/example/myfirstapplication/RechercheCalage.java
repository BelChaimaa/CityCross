package com.example.myfirstapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;

import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;


public class RechercheCalage extends AppCompatActivity {

    LocationManager locationManager;

    private ProgressBar progressBar; // widget de chargement lorsque l'appareil cherche la position ou effectue les calculs
    private Button btnChercher;
    private TextInputEditText inputDistance;
    private TextInputEditText inputEllipsoide;
    private TextInputEditText inputNumber;

    private GeodesicData inverseUtilisateurNordMagnetique;

    private Double distance;
    private String numEllipsoide; // nom de l'ellipsoïde

    private double[] coordsUtilisateur;

    private int villesNumber; // nombre de villes à afficher
    private int nbVillesDefault = 10; // nombre de villes à afficher par défault

    private final double[] coordsNordMagnetique = {81.08, -73.13}; // utilisé pour calculer la déclinaison magnétique


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_calage);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RechercheCalage.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        } else {
            Log.d("__pos__", "cherche...");
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, locationListenerGPS);
            // la position est actualisée toute les 10 secondes

            long maxCounter = 5000;
            long diff = 1000;

            new CountDownTimer(maxCounter, diff) {
                // on compte 5 secondes, si la position n'a pas été trouvé avec le GPS on cherche avec une autre méthode moins précise utilisant internet
                public void onTick(long millisUntilFinished) {
                    long diff = maxCounter - millisUntilFinished;
                }

                public void onFinish() {
                    if (ActivityCompat.checkSelfPermission(RechercheCalage.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(RechercheCalage.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Log.d("__pos__", "GPS prend trop de temps, on essai internet");
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, locationListenerGPS);
                }
            }.start();
        }

        progressBar = findViewById(R.id.progressBar);
        inputNumber = findViewById(R.id.inputNumber);

        btnChercher = findViewById(R.id.btnChercher);
        inputDistance = findViewById(R.id.inputDistance);
        inputEllipsoide = findViewById(R.id.inputEllipsoide);

        btnChercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    btnChercher.setEnabled(false);
                    distance = Double.parseDouble(inputDistance.getText().toString());
                    numEllipsoide = inputEllipsoide.getText().toString();
                    try {
                        villesNumber = Integer.parseInt(inputNumber.getText().toString());
                    } catch(NumberFormatException e){
                        villesNumber = nbVillesDefault;
                    }

                    openActivitycalage();
                    Log.d("__distance__", String.valueOf(distance));
                } catch (NullPointerException e) {
                    Log.d("Error", String.valueOf(e)); // l'appareil n'a pas encore été géolocalisé
                    progressBar.setVisibility(View.VISIBLE);
                    btnChercher.setEnabled(false);
                }
            }
        });
    }

    public void openActivitycalage() {
        Intent intent = new Intent(this, calage.class);
        Bundle b = new Bundle();
        b.putDouble("distance", distance);
        b.putString("numEllipsoide", numEllipsoide);
        b.putDoubleArray("coordsUtilisateur", coordsUtilisateur);
        b.putInt("villesNumber", villesNumber);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }

    LocationListener locationListenerGPS=new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("display location", "Latitude: "+location.getLatitude()+", longitude: "+location.getLongitude());
            coordsUtilisateur = new double[]{location.getLatitude(), location.getLongitude()};
            if(progressBar.getVisibility() == View.VISIBLE){
                progressBar.setVisibility(View.INVISIBLE);
                btnChercher.setEnabled(true);
            }
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
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RechercheCalage.this);

            alertDialog.setTitle("GPS");
            alertDialog.setMessage("Votre GPS n'est pas activé, l'application ne peut pas fonctionner sans votre localisation.");
            alertDialog.setPositiveButton("Paramêtres", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    RechercheCalage.this.startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(RechercheCalage.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            alertDialog.show();
        }
    };
}