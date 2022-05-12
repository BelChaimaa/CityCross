package com.example.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Export extends AppCompatActivity {

    private Button btnStockageGuidage;
    private Button btnStockageCalage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

        btnStockageGuidage = findViewById(R.id.btnStockageGuidage);
        btnStockageCalage = findViewById(R.id.btnStockageCalage);

        btnStockageGuidage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityStockageGuidage();
            }
        });

        btnStockageCalage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityStockageCalage();
            }
        });
    }

    public void openActivityStockageGuidage() {
        Intent intent=new Intent(this,StockageGuidage.class);
        startActivity(intent);
    }

    public void openActivityStockageCalage() {
        Intent intent=new Intent(this,StockageCalage.class);
        startActivity(intent);
    }

    private static String readOnFile(Context context, File file){
        String result = null;
        if (file.exists()) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(file));
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();
                    while (line != null) {
                        sb.append(line);
                        sb.append("\n");
                        line = br.readLine();
                    }
                    result = sb.toString();
                }
                finally {
                    br.close();
                }
            }
            catch (IOException e) {
                Toast.makeText(context, "Erreur", Toast.LENGTH_LONG).show();
            }
        }
        return result;
    }

    // ---

    private static void writeOnFile(Context context, String text, File file){
        try {
            file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(file);
            Writer w = new BufferedWriter(new OutputStreamWriter(fos));
            try {
                w.write(text);
                w.flush();
                fos.getFD().sync();
            } finally {
                w.close();
                Toast.makeText(context, "Sauvegardé!", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erreur", Toast.LENGTH_LONG).show();
        }
    }

    public static String getTextFromStorage(File rootDestination, Context context, String fileName, String folderName){
        File file = createOrGetFile(rootDestination, fileName, folderName);
        return readOnFile(context, file);
    }

    public static void setTextInStorage(File rootDestination, Context context, String fileName, String folderName, String text){
        File file = createOrGetFile(rootDestination, fileName, folderName);
        writeOnFile(context, text, file);
    }

    private static File createOrGetFile(File destination, String fileName, String folderName){
        File folder = new File(destination, folderName);
        return new File(folder, fileName);
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state));
    }
}