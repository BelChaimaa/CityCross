package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;

public class guidage extends AppCompatActivity {

    private Button btnVille;
    private TextInputEditText inputVille;

    /* Attributs temporaires utilisés pour les tests en attendant leur implémentation dans les classes dédiées */
    private float coordsVille[] = {10, 10};
    private float distance = 100;
    private float seuilAngle = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidage);

        btnVille = findViewById(R.id.btnVille);
        inputVille = findViewById(R.id.inputVille);

        btnVille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("input", inputVille.getText().toString());
            }
        });
    }


}