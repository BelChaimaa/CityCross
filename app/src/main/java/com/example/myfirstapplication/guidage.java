package com.example.myfirstapplication;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.Settings;

import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import net.sf.geographiclib.*; // librairie externe qui permet d'effectuer les calculs géodésiques comme le problème inverse, situé dans app/libs

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class guidage extends AppCompatActivity implements Orientation.Listener {

    private static final int RC_STORAGE_WRITE_PERMS = 100;

    private static final String FILENAME = "Guidage.txt";
    private static final String FOLDERNAME = "CityCross/Guidage";

    final int red = Color.parseColor("#F44336");
    final int green = Color.parseColor("#4CAF50");

    LocationManager locationManager;

    private ConstraintLayout initLayout;
    private ProgressBar progressBar;
    private Button btnChercher;
    private TextInputEditText inputVille;
    private TextInputEditText inputEllipsoide;
    private TextView txtS12;
    private FloatingActionButton btnExport;
    private SearchView fileNameInput;

    private GuidageView guidageView;

    private Orientation mOrientation;
    private boolean corrige = false;

    private double[] coordsNordMagnetique = {81.08, -73.13};

    private GeodesicData inverseUtilisateurNordMagnetique;
    private GeodesicData inverseUtilisateurVille;

    private double[] coordsUtilisateur;
    private double direction;
    private String nomVille;
    private String numEllipsoide;
    private double[] coordsVille;
    private double[] paramEllipsoide;
    private double distance;
    private double seuilAngle = 10;

    private boolean useDefault;

    private String ip = "192.168.1.51";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidage);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(guidage.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        } else {
            Log.d("__pos__", "cherche...");
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, locationListenerGPS);

            long maxCounter = 5000;
            long diff = 1000;

            new CountDownTimer(maxCounter, diff) {

                public void onTick(long millisUntilFinished) {
                    long diff = maxCounter - millisUntilFinished;
                }

                public void onFinish() {
                    if (ActivityCompat.checkSelfPermission(guidage.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(guidage.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Log.d("__pos__", "GPS prend trop de temps, on essai internet");
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, locationListenerGPS);
                }
            }.start();
        }

        initLayout = findViewById(R.id.initLayout);
        progressBar = findViewById(R.id.progressBar);
        btnChercher = findViewById(R.id.btnChercher);
        inputVille = findViewById(R.id.inputDistance);
        inputEllipsoide = findViewById(R.id.inputEllipsoide);
        txtS12 = findViewById(R.id.txtS12);
        btnExport = findViewById(R.id.btnExport);
        fileNameInput = findViewById(R.id.fileNameInput);

        mOrientation = new Orientation(this);

        btnChercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useDefault = true;
                try {
                    btnChercher.setEnabled(false);
                    nomVille = inputVille.getText().toString();
                    numEllipsoide = inputEllipsoide.getText().toString();

                    bgEllipsoide backgroundEllipsoide = new bgEllipsoide(getApplicationContext());
                    backgroundEllipsoide.execute(numEllipsoide);
                    bg background = new bg(getApplicationContext());
                    background.execute(nomVille);
                    Log.d("input", nomVille);
                } catch (NullPointerException e) {
                    Log.d("Error", String.valueOf(e)); // l'appareil n'a pas encore été géolocalisé
                    progressBar.setVisibility(View.VISIBLE);
                    btnChercher.setEnabled(false);
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
            btnChercher.setEnabled(true);
            direction = azimuth + inverseUtilisateurNordMagnetique.azi1;
            //Log.d("___direction___", String.valueOf(azimuth));
            initLayout.removeView(guidageView);
            guidageView = new GuidageView(this, inverseUtilisateurVille.azi1, direction);
            initLayout.addView(guidageView);
            if(Math.abs(direction - inverseUtilisateurVille.azi1) < seuilAngle){
                initLayout.setBackgroundColor(green);
            } else {
                initLayout.setBackgroundColor(red);
            }
        }
    }

    LocationListener locationListenerGPS=new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("__location__", "Latitude: "+location.getLatitude()+", longitude: "+location.getLongitude());
            coordsUtilisateur = new double[]{location.getLatitude(), location.getLongitude()};
            progressBar.setVisibility(View.INVISIBLE);
            btnChercher.setEnabled(true);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("__location__", "location = null");
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(guidage.this);

            alertDialog.setTitle("GPS");
            alertDialog.setMessage("Votre GPS n'est pas activé, l'application ne peut pas fonctionner sans votre localisation.");
            alertDialog.setPositiveButton("Paramêtres", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    guidage.this.startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(guidage.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            alertDialog.show();
        }
    };

    public String createResultString(){
        String res = "Nom: " + nomVille + " Azimuth: " + String.format("%.3f", inverseUtilisateurVille.azi1) + " Distance: " + String.format("%.2f", inverseUtilisateurVille.s12) + " mètres Ellispoïde: " + numEllipsoide + "\n";
        Log.d("___res___", res);
        return res;
    }

    private void writeOnExternalStorage(String fileName) {
        if (Export.isExternalStorageWritable()) {
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            Export.setTextInStorage(directory, guidage.this, fileName, FOLDERNAME, createResultString());
        } else {
            Toast.makeText(guidage.this, "Impossible d'exporter les données dans le stockage externe, veuillez vérifier les autorisations de l'application.", Toast.LENGTH_LONG).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == RC_STORAGE_WRITE_PERMS) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readFromStorage();
            }
        }
    }

    private boolean checkWriteExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{WRITE_EXTERNAL_STORAGE},
                    RC_STORAGE_WRITE_PERMS);
            return true;
        }
        return false;
    }

    private void readFromStorage(){
        if (checkWriteExternalStoragePermission()) return;
    }



    private class bg extends AsyncTask<String, Void, double[]> {

        Context c;

        public bg(Context context){
            this.c = context;
        }

        @Override
        protected double[] doInBackground(String... strings) {
            String result = "";
            String nomVille = strings[0];
            String connexionVille = "http://"+ip+"/logVille.php";
            try {
                URL url = new URL(connexionVille);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
                String data = URLEncoder.encode("nomVille", "UTF-8") + "=" + URLEncoder.encode(nomVille, "UTF-8");
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
                String stringCoords = jArray.getJSONObject(0).getString("Coordinates");
                double latVille = Double.parseDouble(stringCoords.split(",")[0]);
                double longVille = Double.parseDouble(stringCoords.split(",")[1]);
                coordsVille = new double[]{latVille, longVille};
                return coordsVille;
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
            return coordsVille;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(double[] coordsVille) {
            super.onPostExecute(coordsVille);
            try {
                if (useDefault) {
                    inverseUtilisateurVille = Geodesic.WGS84.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsVille[0], coordsVille[1]);
                    inverseUtilisateurNordMagnetique = Geodesic.WGS84.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsNordMagnetique[0], coordsNordMagnetique[1]);
                } else {
                    Geodesic geo = new Geodesic(paramEllipsoide[0], paramEllipsoide[1]);
                    inverseUtilisateurVille = geo.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsVille[0], coordsVille[1]);
                    inverseUtilisateurNordMagnetique = geo.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsNordMagnetique[0], coordsNordMagnetique[1]);
                }
                corrige = true;
                //txtS12.setEnabled(true);
                NumberFormat format = new DecimalFormat("0.#");
                String distanceMessage = getString(R.string.distance, String.format("%.2f", inverseUtilisateurVille.s12));

                Log.d("___dist", distanceMessage);
                txtS12.setText(distanceMessage);

                File directory = getFilesDir();
                String res = createResultString();
                Export.setTextInStorage(directory, guidage.this, FILENAME, FOLDERNAME, res);

                btnExport.setEnabled(true);
                btnExport.setVisibility(View.VISIBLE);
                btnExport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(fileNameInput.getVisibility() == View.VISIBLE){
                            fileNameInput.setVisibility(View.INVISIBLE);
                        } else {
                            fileNameInput.setVisibility(View.VISIBLE);
                        }
                        fileNameInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                guidage.this.writeOnExternalStorage(query);
                                fileNameInput.setVisibility(View.INVISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                return false;
                            }
                        });
                    }
                });
            } catch(NullPointerException e){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(guidage.this);

                alertDialog.setTitle("Ville");
                alertDialog.setMessage("La ville renseignée n'est pas reconnu par la base de données. Veuillez écrire une autre ville.");
                alertDialog.setPositiveButton("retour", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
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
            numEllipsoide = strings[0];
            if(numEllipsoide.equals("")){
                numEllipsoide = "WGS84";
            }
            String connexionEllipsoide = "http://"+ip+"/logEllipsoide.php";
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
                Log.d("___result___", result);
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
                numEllipsoide = "WGS84";
                useDefault = true;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(guidage.this);

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