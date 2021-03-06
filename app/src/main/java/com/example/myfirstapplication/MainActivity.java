package com.example.myfirstapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {
//on initialise les boutons qui sont en fait des layouts
    private LinearLayout calage;
    private LinearLayout guidage;
    private LinearLayout savoirPlus;
    private LinearLayout export;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//on recupere les boutons par id
        calage= findViewById(R.id.calage);
        guidage=findViewById(R.id.guidage);
        savoirPlus=findViewById(R.id.information);
        export =findViewById(R.id.export);

        //ajouter evenement setOnclickListener sur les boutons
        calage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //appeler des methodes implémentées dans la classe mainActivity
                openActivityRechercheCalage();

            }
        });

        guidage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityRechercheGuidage();

            }
        });

        savoirPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySavoirPlus();

            }
        });

        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityExport();

            }
        });
    }

    public void openActivityRechercheGuidage() {
        Intent intent=new Intent(this,RechercheGuidage.class);
        startActivity(intent);
    }


    public void openActivityRechercheCalage(){
        Intent intent=new Intent(this,RechercheCalage.class);
        startActivity(intent);
    }

    public void openActivitySavoirPlus(){
        Intent intent=new Intent(this,SavoirPlus.class);
        startActivity(intent);
    }

    // export permet d'accéder aux dernières recherches effectuée par l'utilisateur, le stockage est interne (dans l'application)
    public void openActivityExport(){
        Intent intent=new Intent(this,Export.class);
        startActivity(intent);
    }
}