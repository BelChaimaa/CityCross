package com.example.myfirstapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class calage extends AppCompatActivity implements Orientation.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calage);
    }

    @Override
    public void onOrientationChanged(double azimuth) {

    }
}