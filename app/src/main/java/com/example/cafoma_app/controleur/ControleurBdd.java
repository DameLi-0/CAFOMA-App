package com.example.cafoma_app.controleur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.cafoma_app.entite.Formation;
import com.example.cafoma_app.entite.User;
import com.example.cafoma_app.model.AccesBdd;

import java.util.List;

public final class ControleurBdd implements Controleur {
    private static String TAG = "ControleurBdd";
    private static ControleurBdd instance = null ;
    private static AccesBdd accesBdd;
    private Formation formation;
    private static List<Formation> formationList;
    private User user;

    private ControleurBdd(){
        super();
    }

    public static final ControleurBdd getInstance(Context context){
        if(ControleurBdd.instance == null){
            ControleurBdd.instance = new ControleurBdd();
            accesBdd = new AccesBdd(context);
            formationList = accesBdd.getListFormation();
        }
        return ControleurBdd.instance;
    }
    public void persisterFormation(Formation formation){
        accesBdd.persister(formation);
        formationList.add(formation);
    }
    public List<Formation> getFormationList(){
        return formationList;
    }
    public void setFormation(Formation formation){
        this.formation = formation;
    }

    public Formation getFormation() {
        return formation;
    }
    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initUser(String username, String password, View view, Intent intent, Activity activity) {

    }

    @Override
    public User getUser() {
        return null;
    }


}
