package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;
import net.sf.geographiclib.*;

public class guidage extends AppCompatActivity implements Orientation.Listener {

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
    /* Attributs temporaires utilisés pour les tests en attendant leur implémentation dans les classes dédiées */
    private double[] coordsVille = {21.422510, 39.826168};
    private double[] coordsUtilisateur = {48.82940795723713, 2.3740972728292586};
    private double distance;
    private double seuilAngle = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidage);

        btnVille = findViewById(R.id.btnVille);
        inputVille = findViewById(R.id.inputVille);
        txtDirection = findViewById(R.id.txtDirection);
        txtAz1 = findViewById(R.id.txtAz1);
        txtS12 = findViewById(R.id.txtS12);

        mOrientation = new Orientation(this);

        btnVille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("input", inputVille.getText().toString());
                inverseUtilisateurVille = Geodesic.WGS84.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsVille[0], coordsVille[1]);
                inverseUtilisateurNordMagnetique = Geodesic.WGS84.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsNordMagnetique[0], coordsNordMagnetique[1]);
                corrige = true;
                txtAz1.setText(inverseUtilisateurVille.azi1 + " ");
                txtS12.setText(inverseUtilisateurNordMagnetique.azi1 + " ");
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
            txtDirection.setText(Double.toString(azimuth + inverseUtilisateurNordMagnetique.azi1));
        } else {
            txtDirection.setText(Double.toString(azimuth));
        }

    }
}