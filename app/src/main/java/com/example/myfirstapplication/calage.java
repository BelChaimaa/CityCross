package com.example.myfirstapplication;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
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
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;

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
import java.util.ArrayList;

public class calage extends AppCompatActivity implements Orientation.Listener {
    // cette fonctionnalité permet, à partir du nom d'une distance, d'obtenir le nom, la distance et la direction sur un ellipsoïde donné des villes qui sont à cette distance (à un certain seuil près)

    private static final int RC_STORAGE_WRITE_PERMS = 100;

    private static final String FILENAME = "Calage.txt"; // utilisé uniquement pour le stockage interne (dans l'application)
    private static final String FOLDERNAME = "CityCross/Calage";

    LocationManager locationManager;

    private ConstraintLayout initLayout;

    private TextView txtS12;
    private FloatingActionButton btnExport;
    private SearchView fileNameInput; // petite barre de recherche pour taper le nom du fichier externe lorsqu'on souhaite exporter les recherches effectuées
    private ProgressBar progressBar;

    private CalageView calageView; // canvas ou les elements graphiques sont dessinés

    private Orientation mOrientation;
    private boolean corrige = false; // vraie si on a calculé la valeur de la déclinaison magnétique

    private final double[] coordsNordMagnetique = {81.08, -73.13}; // utilisé pour calculer la déclinaison magnétique

    private GeodesicData inverseUtilisateurNordMagnetique;
    private GeodesicData inverseUtilisateurVille;

    private double[] coordsUtilisateur;
    private double direction; // en degrés
    private String numEllipsoide; // nom de l'ellipsoïde
    private ArrayList<Object[]> villes = new ArrayList<>(); // villes qui seront affichées, en fonction du nombre de ville spécifié et de l'algorithme de sélection des villes
    private ArrayList<Object[]> villesAll = new ArrayList<>(); // villes qui seront dans le fichier externe si l'utilisateur exporte les données, toute les villes qui sont à la distance spécifiée
    private double[] paramEllipsoide;
    private double distance;
    private double seuil = 10000; // en mètres, ici 10km
    private double seuilAngle = 20; // si une ville est à moins de cette valeur en degrés d'une autre de plus grande population alors elle n'est pas affichée
    private int nbVillesDefault = 10; // nombre de villes à afficher par défault
    private int villesNumber; // nombre de villes à afficher

    private boolean useDefault; // si cette valeur est vraie on va utiliser l'ellipsoïde GRS 1980

    private String ip = "192.168.1.51";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calage);

        initLayout = findViewById(R.id.initLayout);
        txtS12 = findViewById(R.id.txtS12);
        btnExport = findViewById(R.id.btnExport);
        fileNameInput = findViewById(R.id.fileNameInput);
        progressBar = findViewById(R.id.progressBar);

        mOrientation = new Orientation(this);

        Bundle b = getIntent().getExtras();
        distance = b.getDouble("distance");
        numEllipsoide = b.getString("numEllipsoide");
        coordsUtilisateur = b.getDoubleArray("coordsUtilisateur");
        villesNumber = b.getInt("villesNumber");

        inverseUtilisateurNordMagnetique = Geodesic.WGS84.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsNordMagnetique[0], coordsNordMagnetique[1]);

        calage.bgEllipsoide backgroundEllipsoide = new calage.bgEllipsoide(getApplicationContext());
        backgroundEllipsoide.execute(numEllipsoide);

        calage.bg background = new calage.bg(getApplicationContext());
        background.execute(distance);
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
        // à chaque détection de changement d'orientation du téléphone on déclenche la fonction qui créée une instance de la classe CalageView
        if(corrige){
            direction = azimuth + inverseUtilisateurNordMagnetique.azi1;
            initLayout.removeView(calageView);
            calageView = new CalageView(this, txtS12, villes, direction);
            initLayout.addView(calageView);
        }
    }

    public String createResultString(){
        // ce qui sera affiché dans les fichiers de stockages (interne et externe)
        String res = "";
        for(Object[] ville: villesAll){
            res += "Nom: " + ville[0] + " Azimuth: " + ville[1] + " Distance: " + ville[2] + " mètres Ellipsoïde: " + numEllipsoide + "\n";
        }
        return res;
    }

    private void writeOnExternalStorage(String fileName) {
        if (Export.isExternalStorageWritable()) {
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            Export.setTextInStorage(directory, calage.this, fileName+".txt", FOLDERNAME, createResultString());
        } else {
            Toast.makeText(calage.this, "Impossible d'exporter les données dans le stockage externe, veuillez vérifier les autorisations de l'application.", Toast.LENGTH_LONG).show();
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

    private class bg extends AsyncTask<Double, Void, ArrayList<Object[]>> {
        // classe asynchrone qui permet la connection avec la base de données des villes, le paramètre d'entrée est la distance entrée par l'utilisateur

        Context c;

        public bg(Context context){
            this.c = context;
        }

        @Override
        protected ArrayList<Object[]> doInBackground(Double... doubles) {
            Log.d("__async__", "doinbackground");
            String result = "";
            Double distance = doubles[0];
          
            String connexion = "http://"+ip+"/logVilleCalage.php";
            
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
                int i = 0;
                int n = 0;
                while(villes.size() < villesNumber && n < jArray.length()){
                    String stringCoords = jArray.getJSONObject(n).getString("Coordinates");
                    String nom = jArray.getJSONObject(n).getString("Name");
                    double[] coordsVille = {Double.parseDouble(stringCoords.split(",")[0]), Double.parseDouble(stringCoords.split(",")[1])};
                    // on utilise la librairies geolib.jar pour faire le calcul du problème inverse sur toutes les villes afin d'obtenir leur orientatio et distance à partir de leurs coordonnées
                    if(useDefault){
                        inverseUtilisateurVille = Geodesic.WGS84.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsVille[0], coordsVille[1]);
                    } else { // cas par défault, ellipsoïde GRS 1980
                        Geodesic geo = new Geodesic(paramEllipsoide[0], paramEllipsoide[1]);
                        inverseUtilisateurVille = geo.Inverse(coordsUtilisateur[0], coordsUtilisateur[1], coordsVille[0], coordsVille[1]);
                    }
                    if(Math.abs(inverseUtilisateurVille.s12 - distance) < seuil){
                        boolean correct = true;
                        villesAll.add(new Object[]{nom, Math.round(inverseUtilisateurVille.azi1*1000.0)/1000.0, String.format("%.2f", inverseUtilisateurVille.s12)}); // on garde une précision au milième pour l'azimuth et au centième pour la distance
                        for(Object[] ville: villes){
                            if(Math.abs(inverseUtilisateurVille.azi1 - Double.parseDouble(ville[1].toString())) < seuilAngle){
                                correct = false;
                                break;
                            }
                        }
                        if(correct){
                            Object[] v = new Object[]{nom, Math.round(inverseUtilisateurVille.azi1*1000.0)/1000.0, String.format("%.2f", inverseUtilisateurVille.s12)};
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
                Log.d("_______", "_____");
                return villes;
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) { // échec de connexion
                Log.d("___connexion___", String.valueOf(e));
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

            progressBar.setVisibility(View.INVISIBLE);
            corrige = true;
            Log.d("__villes__", String.valueOf(villes));

            File directory = getFilesDir();
            String res = createResultString();
            Export.setTextInStorage(directory, calage.this, FILENAME, FOLDERNAME, res);

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
                            calage.this.writeOnExternalStorage(query);
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
        }
    }

    private class bgEllipsoide extends AsyncTask<String, Void, double[]> {
        //classe asynchrone qui permet la connexion avec la base de données des ellipsoïdes, prend en paramêtre le nom d'un ellipsoïde

        Context c;

        public bgEllipsoide(Context context) {
            this.c = context;
        }

        private boolean getParamMethod1(String textEllipsoideString){
            // méthode qui récupère les paramêtres de l'ellipsoïde à partir de la deuxième colonne de la base de données
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
            // méthode qui récupère les paramêtres de l'ellipsoïde à partir de la troisième colonne de la base de données
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
            if(numEllipsoide.equals("")){
                numEllipsoide = "GRS 1980";
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
                } else { // dans certains cas la deuxième colonne ne contient pas les paramêtres de l'ellipsoïde
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
            if (numEllipsoide.equalsIgnoreCase("grs 1980")){
                useDefault = true;
            } else if(paramEllipsoide[0] == 0 && paramEllipsoide[1] == 0) { // ellipsoïde inconnue
                numEllipsoide = "GRS 1980";
                useDefault = true;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(calage.this);

                alertDialog.setTitle("Ellipsoïde");
                alertDialog.setMessage("L'ellipsoïde renseigné n'est pas reconnu par la base de données. L'ellipsoïde GRS 1980 a été utilisé par défault");
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