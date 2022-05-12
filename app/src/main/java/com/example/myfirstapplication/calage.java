package com.example.myfirstapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.google.android.material.textfield.TextInputEditText;

import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class calage extends AppCompatActivity implements Orientation.Listener {

    LocationManager locationManager;

    private ConstraintLayout initLayout;
    private ProgressBar progressBar;
    private Button btnVille;
    private TextInputEditText inputDistance;
    private TextInputEditText inputEllipsoide;
    private TextInputEditText inputNumber;
    private TextView txtDirection;
    private TextView txtAz1;
    private TextView txtS12;

    private CalageView calageView;

    private Orientation mOrientation;
    private boolean corrige = false;

    private double[] coordsNordMagnetique = {81.08, -73.13};

    private GeodesicData inverseUtilisateurNordMagnetique;
    private GeodesicData inverseUtilisateurVille;

    private double[] coordsUtilisateur;
    private double direction;
    private String numEllipsoide;
    private ArrayList<Object[]> villes = new ArrayList<>();
    private double[] paramEllipsoide;
    private double distance;
    private double seuil = 1000;
    private double seuilAngle = 20;

    private int villesNumber;

    private boolean useDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calage);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(calage.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        } else {
            Log.d("__pos__", "cherche...");
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListenerGPS);

            long maxCounter = 20000;
            long diff = 1000;

            new CountDownTimer(maxCounter, diff) {

                public void onTick(long millisUntilFinished) {
                    long diff = maxCounter - millisUntilFinished;
                }

                public void onFinish() {
                    if (ActivityCompat.checkSelfPermission(calage.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(calage.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Log.d("__pos__", "GPS prend trop de temps, on essai internet");
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, locationListenerGPS);
                }
            }.start();
        }

        initLayout = findViewById(R.id.initLayout);
        progressBar = findViewById(R.id.progressBar);
        btnVille = findViewById(R.id.btnChercher);
        inputDistance = findViewById(R.id.inputDistance);
        inputEllipsoide = findViewById(R.id.inputEllipsoide);
        inputNumber = findViewById(R.id.inputNumber);
        txtDirection = findViewById(R.id.txtDirection);
        txtAz1 = findViewById(R.id.txtAz1);
        txtS12 = findViewById(R.id.txtS12);

        mOrientation = new Orientation(this);

        btnVille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    distance = Double.parseDouble(inputDistance.getText().toString());
                    numEllipsoide = inputEllipsoide.getText().toString();
                    villesNumber = Integer.parseInt(inputNumber.getText().toString());

                    calage.bgEllipsoide backgroundEllipsoide = new calage.bgEllipsoide(getApplicationContext());
                    backgroundEllipsoide.execute(numEllipsoide);

                    calage.bg background = new calage.bg(getApplicationContext());
                    background.execute(distance);
                    Log.d("__distance__", String.valueOf(distance));
                } catch (NullPointerException e) {
                    Log.d("Error", String.valueOf(e)); // l'appareil n'a pas encore été géolocalisé
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            mOrientation.startListening(this);
        } catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        mOrientation.stopListening();
    }


    @Override
    public void onOrientationChanged(double azimuth) {
        if(corrige){
            direction = azimuth + inverseUtilisateurNordMagnetique.azi1;
            txtDirection.setText(Double.toString(direction));
            initLayout.removeView(calageView);
            calageView = new CalageView(this, villes, direction);
            initLayout.addView(calageView);
        } else {
            txtDirection.setText(Double.toString(azimuth));
        }
    }

    LocationListener locationListenerGPS=new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("display location", "Latitude: "+location.getLatitude()+", longitude: "+location.getLongitude());
            coordsUtilisateur = new double[]{location.getLatitude(), location.getLongitude()};
            progressBar.setVisibility(View.INVISIBLE);
            inverseUtilisateurNordMagnetique = Geodesic.WGS84.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsNordMagnetique[0], coordsNordMagnetique[1]);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("display location", "location = null");
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(calage.this);

            alertDialog.setTitle("GPS");
            alertDialog.setMessage("Votre GPS n'est pas activé, l'application ne peut pas fonctionner sans votre localisation.");
            alertDialog.setPositiveButton("Paramêtres", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    calage.this.startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(calage.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            alertDialog.show();
        }
    };

    private class bg extends AsyncTask<Double, Void, ArrayList<Object[]>> {

        Context c;

        public bg(Context context){
            this.c = context;
        }

        @Override
        protected ArrayList<Object[]> doInBackground(Double... doubles) {
            Log.d("__async__", "doinbackground");
            String result = "";
            Double distance = doubles[0];
            //changer l'adresse ip d l'ordi

            String connexion = "http://10.181.201.63/logVilleCalage.php";
            try {
                URL url = new URL(connexion);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
                String data = URLEncoder.encode("distance", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(distance), "UTF-8");
                writer.write(data);
                writer.flush();
                writer.close();
                InputStream ips = http.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
                String line;
                while((line = reader.readLine()) != null){
                    result += line;
                }
                reader.close();
                ips.close();
                http.disconnect();
                JSONArray jArray = new JSONArray(result);
                Log.d("___result___", result);
                villesNumber = 30; // = jArray.length() si on veut afficher toutes les villes
                int i = 0;
                int n = 0;
                while(villes.size() < villesNumber && n < jArray.length()){
                    String stringCoords = jArray.getJSONObject(n).getString("Coordinates");
                    String nom = jArray.getJSONObject(n).getString("Name");
                    double[] coordsVille = {Double.parseDouble(stringCoords.split(",")[0]), Double.parseDouble(stringCoords.split(",")[1])};
                    if(useDefault){
                        inverseUtilisateurVille = Geodesic.WGS84.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsVille[0], coordsVille[1]);
                        //inverseUtilisateurNordMagnetique = Geodesic.WGS84.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsNordMagnetique[0], coordsNordMagnetique[1]);
                    } else {
                        Geodesic geo = new Geodesic(paramEllipsoide[0], paramEllipsoide[1]);
                        inverseUtilisateurVille = geo.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsVille[0], coordsVille[1]);
                        //Log.d("__test0__", String.valueOf(inverseUtilisateurVille.s12));
                    }
                    if(Math.abs(inverseUtilisateurVille.s12 - distance) < seuil){
                        boolean correct = true;
                        for(Object[] ville: villes){
                            if(Math.abs(inverseUtilisateurVille.azi1 - Double.parseDouble(ville[1].toString())) < seuilAngle){
                                correct = false;
                                break;
                            }
                        }
                        if(correct){
                            Object[] v = new Object[]{nom, inverseUtilisateurVille.azi1};
                            try {
                                i++;
                                villes.add(v);
                                Log.d("__ville__", String.valueOf(i));
                            } catch(NullPointerException e){
                                Log.d("__null__", "v");
                            }
                        }
                    }
                    n++;
                }

                return villes;
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) { // échec de connexion
                Log.d("___connexion___", "error");
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return villes;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Object[]> villes) {
            super.onPostExecute(villes);
            corrige = true;
            Log.d("__villes__", String.valueOf(villes));
        }
    }

    private class bgEllipsoide extends AsyncTask<String, Void, double[]> {

        Context c;

        public bgEllipsoide(Context context) {
            this.c = context;
        }

        private boolean getParamMethod1(String textEllipsoideString){
            String aS = "";
            String bS = "";
            if(textEllipsoideString.matches(".*\\SPHEROID\\b.*")){
                if(textEllipsoideString.split("SPHEROID")[1].split(",").length > 1){
                    aS = textEllipsoideString.split("SPHEROID")[1].split(",")[1];
                    bS = textEllipsoideString.split("SPHEROID")[1].split(",")[2];
                    if(aS.matches("[+-]?\\d*(\\.\\d+)?") && bS.matches("[+-]?\\d*(\\.\\d+)?") && aS != "" && bS != "" ){
                        Double a = Double.parseDouble(aS);
                        Double f = 1/Double.parseDouble(bS);
                        paramEllipsoide = new double[]{a, f};
                        return true;
                    }
                    return false;
                }
            }
            return false;
        }

        private boolean getParamMethod2(String paramEllipsoideString){
            String aS = "";
            String bS = "";
            int egal = 0;
            for (char c: paramEllipsoideString.toCharArray()) {
                if (egal == 0) {
                    if (c == '=') {
                        egal += 1;
                    }
                } else if (egal == 1) {
                    if (c == '=') {
                        egal += 1;
                    }
                } else if (egal == 2) {
                    if (c == ' ') {
                        egal += 1;
                    } else {
                        aS += c;
                    }
                } else if (egal == 3) {
                    if (c == '=') {
                        egal += 1;
                    }
                } else if (egal == 4) {
                    if (c == ' ') {
                        egal += 1;
                    } else {
                        bS += c;
                    }
                }
            }
            if(aS.matches("[+-]?\\d*(\\.\\d+)?") && bS.matches("[+-]?\\d*(\\.\\d+)?") && aS != "" && bS != "" ) {
                Double a = Double.parseDouble(aS);
                Double b = Double.parseDouble(bS);
                paramEllipsoide = new double[]{a, (a - b) / a};
                return true;
            }
            return false;
        }

        @Override
        protected double[] doInBackground(String... strings) {
            paramEllipsoide = new double[]{0, 0};
            String result = "";
            String numEllipsoide = strings[0];
            //changer l'adresse IP du wifi
            String connexionEllipsoide = "http://10.181.201.63/logEllipsoide.php";
            try {
                URL url = new URL(connexionEllipsoide);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
                String data = URLEncoder.encode("numEllipsoide", "UTF-8") + "=" + URLEncoder.encode(numEllipsoide, "UTF-8");
                writer.write(data);
                writer.flush();
                writer.close();
                InputStream ips = http.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                reader.close();
                ips.close();
                http.disconnect();
                Log.d("___result2___", result);
                JSONArray jArray = new JSONArray(result);
                String paramEllipsoideString = "";
                String textEllipsoideString = "";
                try {
                    textEllipsoideString = jArray.getJSONObject(0).getString("srtext");
                    paramEllipsoideString = jArray.getJSONObject(0).getString("proj4text");
                } catch(JSONException e) {
                    e.printStackTrace();
                }
                if(getParamMethod1(textEllipsoideString)){
                    getParamMethod1(textEllipsoideString);
                } else {
                    getParamMethod2(paramEllipsoideString);
                }
                return paramEllipsoide;
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) { // échec de connexion
                Log.d("___connexion___", "error");
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return paramEllipsoide;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(double[] paramEllipsoide) {
            super.onPostExecute(paramEllipsoide);
            Log.d("___e___", numEllipsoide.toLowerCase());
            if (numEllipsoide.equalsIgnoreCase("wgs84")){
                useDefault = true;
            } else if(paramEllipsoide[0] == 0 && paramEllipsoide[1] == 0) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(calage.this);

                alertDialog.setTitle("Ellipsoïde");
                alertDialog.setMessage("L'ellipsoïde renseigné n'est pas reconnu par la base de données. L'ellipsoïde WGS84 a été utilisé par défault");
                alertDialog.setPositiveButton("retour", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            } else {
                useDefault = false;
            }
            Log.d("____param_____", String.valueOf(paramEllipsoide[0]));
            Log.d("____param_____", String.valueOf(paramEllipsoide[1]));
        }
    }
}