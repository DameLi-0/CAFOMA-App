package com.example.cafoma_app.model;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cafoma_app.controleur.ControleurServeur;
import com.example.cafoma_app.entite.Formation;
import com.example.cafoma_app.entite.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AccesDistant implements ReponseAsyncItf {
    private final static String TAG = "AccesDistant";
    private static final String SERVERADDR = "http://10.0.2.2/CAFOMA/serveurCAFOMA.php";
    private ControleurServeur controleurServeur;
    public AccesDistant(){
        controleurServeur = ControleurServeur.getInstance();
    }
    public Intent intent;
    public Activity activity;

    public AccesDistant(Intent intent, Activity activity){
        super();
        this.intent = intent;
        this.activity = activity;
        controleurServeur = ControleurServeur.getInstance();
    }
    @Override
    public void reponseRequete(String reponse) {
        String[] message = reponse.split("#");
        if(message.length > 1) {
            if (message[0].equals("lister")) {
                try {
                    JSONArray jsonTabFormation = new JSONArray(message[1]);
                    List<Formation> formationList = parserFormationList(jsonTabFormation);
                    controleurServeur.setFormationList(formationList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (message[0].equals("erreur")) {
                Log.i(TAG,"Erreur : " + message[1]);
            }
            else if (message[0].equals("erreur")) {
                Log.i(TAG,"Erreur : " + message[1]);
            }
            if (message[0].equals("login")) {
                try {
                    JSONObject jsonTabLogin = new JSONObject(message[1]);
                    User user = parserUserList(jsonTabLogin);
                    controleurServeur.setUser(user);
                    if (user.getValid().equals("1")){
                        activity.startActivity(intent);
                    }
                    else{
                        Toast.makeText(activity, "Login Failed", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (message[0].equals("erreur")) {
                Log.i(TAG,"Erreur : " + message[1]);
            }
        }
    }
    public void envoyerRequete(String operation){
        RequeteHttp requeteHttp = new RequeteHttp();
        requeteHttp.reponseAsync = this;
        requeteHttp.addParam("operation", operation);
        requeteHttp.execute(SERVERADDR);
    }
    public void envoyerRequeteView(String operation, View view){
        RequeteHttp requeteHttp = new RequeteHttp(view);
        requeteHttp.reponseAsync = this;
        requeteHttp.addParam("operation", operation);
        requeteHttp.execute(SERVERADDR);
    }
    private List<Formation> parserFormationList(JSONArray jsonTabFormation) throws JSONException {
        List<Formation> formationList = new ArrayList<>();
        Formation formation = null;
        for (int i=0; i<jsonTabFormation.length(); i++){
            int formation_id  = jsonTabFormation.getJSONObject(i).getInt("formation_id");
            int fk_user_id  = jsonTabFormation.getJSONObject(i).getInt("fk_user_id");
            String libelle = jsonTabFormation.getJSONObject(i).getString("libelle");
            String acronyme = jsonTabFormation.getJSONObject(i).getString("acronyme");
            String description = jsonTabFormation.getJSONObject(i).getString("description");
            String img = jsonTabFormation.getJSONObject(i).getString("img");
            String video = jsonTabFormation.getJSONObject(i).getString("video");

            formation = new Formation(formation_id, fk_user_id, libelle, acronyme, description, img, video);
            Log.i(TAG, "i=" + i + " formation=" + formation);
            formationList.add(formation);
        }
        return formationList;
    }
    private User parserUserList(JSONObject jsonUser) throws JSONException {
        User user = null;
        List<Formation> formations = null;
            String login = jsonUser.getString("login");
            Boolean verif = jsonUser.getBoolean("verif");
            formations = parserFormationList(jsonUser.getJSONArray("formations"));
            if (jsonUser.getJSONArray("formations").getJSONObject(0).getInt("formation_id") == -1){
                user = new User(login,verif);
            }
            else{
                user = new User(login,verif,formations);
            }
        return user;
    }
}
