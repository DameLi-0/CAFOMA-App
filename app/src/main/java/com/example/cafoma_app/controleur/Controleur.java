package com.example.cafoma_app.controleur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.cafoma_app.entite.Formation;
import com.example.cafoma_app.entite.User;

import java.util.List;

public interface Controleur {
    List<Formation> getFormationList();
    void setFormation(Formation formation);
    Formation getFormation();
    User getUser();
    void setUser(User user);
    void initUser(String username, String password, View view, Intent intent, Activity activity);
}
