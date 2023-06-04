package com.example.cafoma_app.vue;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafoma_app.R;
import com.example.cafoma_app.controleur.Controleur;
import com.example.cafoma_app.controleur.ControleurBdd;
import com.example.cafoma_app.controleur.ControleurServeur;
import com.example.cafoma_app.entite.Formation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    private static String TAG = "Formation Activity";
    private TextView titreView;
    private TextView formation_id;
    private TextView libelle;
    private TextView acronyme;
    private TextView description;
    private ImageView img;
    private FloatingActionButton favori;
    private Controleur controleur;
    private Formation formation;
    private int mode;
    private String titre;
    private ListView liste;
    private ArrayAdapter<String> adapter;
    private ControleurBdd controleurBdd;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getFormationByMode();
        if(formation != null){
            titreView.setText(titre);
            controleur = ControleurServeur.getInstance();
            Log.i(TAG,"mode=" + mode);
            initialisationIhm();
        }
        else {
            titreView.setText("Aucune formation sélectionné");
        }
    }
    private void getFormationByMode(){
        mode = getIntent().getIntExtra("mode", 2);
        titreView = (TextView) findViewById(R.id.titreId);
        if(mode == 1) {
            controleur = ControleurServeur.getInstance();
            titre = "Mes formations";
            formation = controleur.getFormation();
        }
        else if(mode == 3){
            controleurBdd = ControleurBdd.getInstance(this);
            titre = "Favoris";
            formation = controleurBdd.getFormation();
        }
        else {
            controleur = ControleurServeur.getInstance();
            titre = "Formation Serveur";
            formation = controleur.getFormation();
        }
        Log.i(TAG,"formation=" + formation);
    }
    private void initialisationIhm() {
        recupererComposant();
        renseignerComposant();
        if (mode == 1){

        }
        if (mode ==2){
            favori.setVisibility(View.VISIBLE);
            ajouterFavoris();
        }
        if (mode == 3){
            favori.setVisibility(View.VISIBLE);
            ajouterFavoris();
        }
    }
    private void recupererComposant(){
        formation_id = (TextView) findViewById(R.id.formation_id);
        libelle = (TextView) findViewById(R.id.libelle);
        acronyme = (TextView) findViewById(R.id.acronyme);
        favori = findViewById(R.id.buttonFavori);
        description = (TextView) findViewById(R.id.descriptionId);
        img = findViewById(R.id.img);
    }
    private void renseignerComposant(){
        formation_id.setText(Integer.toString(formation.getFormation_id()));
        libelle.setText(formation.getLibelle());
        acronyme.setText(formation.getAcronyme());
        description.setText(formation.getDescription());
        img.setVisibility(View.VISIBLE);
        loadImageView(img,"http://10.0.2.2/formation/public/Training/Image/" + formation.getImg());
    }
    private void loadImageView (ImageView img, String url) {
        Log.i("loadImageView",url);
        new Thread(new Runnable() {
            public void run(){
                try {
                    Log.i(TAG, "loadImageView thread");
                    final Drawable drawable = Drawable.createFromStream((InputStream) new URL(url).getContent(), "src");
                    Log.i(TAG, "loadImageView dawable");
                    Thread.sleep(100); // Pour serveur local
                    img.post(new Runnable() {
                        public void run() {
                            Log.i(TAG,"setImageDrawable");
                            img.setImageDrawable(drawable);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void ajouterFavoris(){

        favori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == 2){
                    Log.i(TAG,"ajoutfavoris");
                    //controleurBdd.persister(formation);
                }
            }
        });

    }




}