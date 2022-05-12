package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

public class StockageCalage extends AppCompatActivity {

    private TextView res;
    File directory;

    private static final String FILENAME = "Calage.txt";
    private static final String FOLDERNAME = "CityCross/Calage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockage_calage);

        res = findViewById(R.id.txt);
        directory = getFilesDir();
        res.setText(Export.getTextFromStorage(directory, this, FILENAME, FOLDERNAME));
    }
}