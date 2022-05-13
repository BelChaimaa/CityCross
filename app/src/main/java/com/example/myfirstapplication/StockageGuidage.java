package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;

public class StockageGuidage extends AppCompatActivity {

    private TextView res;
    File directory;

    private static final String FILENAME = "Guidage.txt";
    private static final String FOLDERNAME = "CityCross/Guidage"; // dossier du fichier externe

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockage_guidage);

        res = findViewById(R.id.txt);
        directory = getFilesDir();
        res.setText(Export.getTextFromStorage(directory, this, FILENAME, FOLDERNAME));
    }
}