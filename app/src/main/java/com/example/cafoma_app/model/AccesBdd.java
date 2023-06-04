package com.example.cafoma_app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.cafoma_app.entite.Formation;

import java.util.ArrayList;
import java.util.List;

public class AccesBdd {
    private static String TAG = "AccesBdd";
    private DatabaseOpenHelper mDbHelper;
    private Context context;

    public AccesBdd(Context context) {
        Log.i(TAG,"AccesBdd");
        mDbHelper = new DatabaseOpenHelper(context);
        this.context = context;
    }

    public void persister(Formation formation){
        SQLiteDatabase dataBase = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseOpenHelper.FORMATION_ID_FIELD, formation.getFormation_id());
        values.put(DatabaseOpenHelper.FK_USER_ID_FIELD, formation.getFk_user_id());
        values.put(DatabaseOpenHelper.LIBELLE_FIELD, formation.getLibelle());
        values.put(DatabaseOpenHelper.ACRONYME_FIELD, formation.getAcronyme());
        values.put(DatabaseOpenHelper.DESCRIPTION_FIELD, formation.getDescription());
        values.put(DatabaseOpenHelper.IMG_FIELD, formation.getImg());
        values.put(DatabaseOpenHelper.VIDEO_FIELD, formation.getVideo());
        dataBase.insert(DatabaseOpenHelper.TABLE_NAME, null, values);
        dataBase.close();
    }

    public List<Formation> getListFormation() {
        Formation formation = null;
        List<Formation> formationList = null;
        SQLiteDatabase dataBase = mDbHelper.getReadableDatabase();
        String req = "select * from " +  DatabaseOpenHelper.TABLE_NAME + " order by " + DatabaseOpenHelper.FORMATION_ID_FIELD;
        Cursor cursor = dataBase.rawQuery(req, null);
        if (cursor.getCount() > 0) {
            formationList = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    try {
                        Integer formation_id = cursor.getInt(0);
                        Integer fk_user_id = cursor.getInt(1);
                        String libelle = cursor.getString(2);
                        String acronyme = cursor.getString(3);
                        String description = cursor.getString(4);
                        String img = cursor.getString(5);
                        String video = cursor.getString(6);

                        formation = new Formation(formation_id, fk_user_id, libelle, acronyme, description, img, video);
                        formationList.add(formation);
                    } catch (SQLException e) {
                        Toast.makeText(this.context, "Impossible de lire les étudiants en BDD", Toast.LENGTH_LONG).show();
                    }
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        Log.i(TAG, "formationList=" + formationList);
        return formationList;
    }
}
