package com.example.myfirstapplication;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Ellipsoide extends AppCompatActivity {
EditText _txtEllip;
Button _btnEllip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ellipsoide);
        _txtEllip = (EditText) findViewById(R.id.txtEllip);
        _btnEllip = (Button) findViewById(R.id.btnEllip);
        _btnEllip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nom = _txtEllip.getText().toString();
                bg background = new bg(getApplicationContext());
                background.execute(nom);
            }
        });
    }
    private class bg extends AsyncTask <String,Void,String> {
        AlertDialog d;
        Context c;

        public bg(Context context) {
            this.c = context;
        }

        @Override
        protected void onPreExecute() {
            d = new AlertDialog.Builder(c).create();
            d.setTitle("Etat de connexion");
        }

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            String nom = strings[0];
            // pour savoir votre adresse ip: lancer la commande "ipconfig" avec le programme cmd (ipv4 du reseau Wifi)
            String connstr = "http://192.168.1.18/geodev/login.php ";
            try {
                URL url = new URL(connstr);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("ellipsoide", "UTF-8") + "=" + URLEncoder.encode(nom, "UTF-8"); // valeur d'ellipsoide saisie par l'utilisateur
                writer.write(data);
                writer.flush();
                writer.close();
                // Canal d'entree
                InputStream ips = http.getInputStream();
                // Lecture des données reçues au niveau du Buffer (echo de login.php)
                BufferedReader reader = new BufferedReader((new InputStreamReader(ips, "ISO-8859-1")));
                String ligne="";
                while ((ligne = reader.readLine()) != null){
                    result = result + ligne;
                }
                // fermeture du lecteur de donnees, du flux d'entree et deconnection
                reader.close();
                ips.close();
                http.disconnect();
                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("error", e.getMessage());
            }
            return result;
        }
        @Override
        protected void onPostExecute(String s){
            d.setMessage(s);
            d.show();
            //Redirection vers une autre interface en cas de succes de connexion
            if (s.contains("succes")){
                Intent i = new Intent(getApplicationContext(), Orientation.class);
                startActivity(i);

            }
        }

    }

}