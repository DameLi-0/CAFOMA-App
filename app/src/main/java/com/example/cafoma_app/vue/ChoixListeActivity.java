package com.example.cafoma_app.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.cafoma_app.R;
import com.example.cafoma_app.controleur.Controleur;
import com.example.cafoma_app.controleur.ControleurServeur;

public class ChoixListeActivity extends AppCompatActivity {
    private static String TAG = "ChoixActivity";
    private Controleur controleur;
    private CardView titreViewFormationsListe;
    private CardView titreViewMesFormations;
    private CardView titreViewMesFavoris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix);
        controleur = ControleurServeur.getInstance();
        titreViewFormationsListe = (CardView) findViewById(R.id.titreId);
        titreViewMesFormations = (CardView) findViewById(R.id.titreId2);
        titreViewMesFavoris = (CardView) findViewById(R.id.titreId3);
        initChoix();
    }
    private void initChoix(){
        titreViewFormationsListe.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ChoixListeActivity.this, ListeActivity.class);
            intent.putExtra("mode", 2);
            startActivity(intent);
        }
    });
        titreViewMesFormations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoixListeActivity.this, ListeActivity.class);
                intent.putExtra("mode", 1);
                startActivity(intent);
            }
        });
        titreViewMesFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoixListeActivity.this, ListeActivity.class);
                intent.putExtra("mode", 3);
                startActivity(intent);
            }
        });
    }
}
