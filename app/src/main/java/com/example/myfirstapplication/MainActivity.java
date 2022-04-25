package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
//on initialise les boutons
    private ToggleButton calage;
    private ToggleButton guidage;
    private ToggleButton savoirPlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//on recupere les boutons par id
        calage= findViewById(R.id.toggleButton);
        guidage=findViewById(R.id.toggleButton3);
        savoirPlus=findViewById(R.id.toggleButton4);
/*
        //ajouter evenement setOnclickListener sur les boutons
        calage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //appeler des methodes implémentées dans la classe mainActivity
                openActivitycalage();

            }
        });*/

        guidage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityguidage();

            }
        });

        savoirPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySavoirPlus();

            }
        });

    }

    public void openActivityguidage() {
        Intent intent=new Intent(this,guidage.class);
        startActivity(intent);
    }
/*

    public void openActivitycalage(){
        Intent intent=new Intent(this,calage.class);
        startActivity(intent);
    }*/

    public void openActivitySavoirPlus(){
        Intent intent=new Intent(this,SavoirPlus.class);
        startActivity(intent);
    }



}