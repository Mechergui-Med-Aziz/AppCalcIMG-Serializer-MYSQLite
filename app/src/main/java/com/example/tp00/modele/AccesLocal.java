package com.example.tp00.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp00.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {
    private String nomBase="bdCoach.sqlite";
    private Integer versionBAse = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    public AccesLocal(Context contexte) {
        accesBD = new MySQLiteOpenHelper(contexte, nomBase,null , versionBAse);
    }

    public void ajoutProfil(Profil profil){
        bd= accesBD.getWritableDatabase();
        String req= "insert into profil (dataMesure, Poids, taille, age, sexe) values ";
                req +="(\""+profil.getDateMesure()+"\","+profil.getPoids()+","+profil.getTaille()+","+profil.getAge()+","+profil.getSexe()+")";
        bd.execSQL(req);
    }
    public Profil recupdernier(){
        bd= accesBD.getReadableDatabase();
        Profil profil = null;
        String req ="select * from profil";
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            Date date = new Date();
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            profil = new Profil(date,poids,taille,age,sexe);
        }
        curseur.close();
        return profil;
    }
}
