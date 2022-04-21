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
    private TextView txtAngle;
    private TextView txtAz1;
    private TextView txtAz2;
    private TextView txtS12;

    private Orientation mOrientation;

    /* Attributs temporaires utilisés pour les tests en attendant leur implémentation dans les classes dédiées */
    private double[] coordsVille = {51.07169495405786, -0.31995637707306734};
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
        txtAngle = findViewById(R.id.txtAngle);
        txtAz1 = findViewById(R.id.txtAz1);
        txtAz2 = findViewById(R.id.txtAz2);
        txtS12 = findViewById(R.id.txtS12);

        mOrientation = new Orientation(this);

        btnVille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("input", inputVille.getText().toString());
                txtAngle.setText(""+Calculs.getAngle(coordsVille, coordsUtilisateur));
                GeodesicData g = Geodesic.WGS84.Inverse(coordsVille[0], coordsVille[1], coordsUtilisateur[0], coordsUtilisateur[1]);
                txtAz1.setText(g.azi1 + " ");
                txtAz2.setText(g.azi2 + " ");
                txtS12.setText(g.s12 + " ");
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
    public void onOrientationChanged(float azimuth) {
        txtDirection.setText(Float.toString(azimuth));
    }
}