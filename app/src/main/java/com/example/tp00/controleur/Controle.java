package com.example.tp00.controleur;

import android.content.Context;

import com.example.tp00.modele.AccesLocal;
import com.example.tp00.modele.Profil;
import com.example.tp00.outils.Serializer;

import java.util.Date;

public final class Controle {
    private static Controle instance=null;
    private static Profil profil;
    private static String nomFic="saveprofil";
    private static AccesLocal accesLocal;


    private Controle() {
        super();
    }
    public static final Controle getInstance(Context contexte){
        if(Controle.instance==null){
            Controle.instance=new Controle();
            accesLocal = new AccesLocal(contexte);
            profil = accesLocal.recupdernier();
            //recupSerialise(contexte);
        }
        return Controle.instance;
    }
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte){
        profil=new Profil(new Date(),poids,taille,age,sexe);
        accesLocal.ajoutProfil(profil);
        //Serializer.serialize(nomFic,profil,contexte);
    }
    public float getImg(){
        return profil.getImg();
    }
    public String getMessage(){
        return profil.getMessage();
    }
    public static void recupSerialise(Context contexte){
        profil = (Profil) Serializer.deSerialize(nomFic, contexte);
    }
    public Integer getPoids(){
        if(profil==null){
            return null;
        }
        else{
            return profil.getPoids();
        }
    }
    public Integer getTaille(){
        if(profil==null){
            return null;
        }
        else{
            return profil.getTaille();
        }
    }
    public Integer getAge(){
        if(profil==null){
            return null;
        }
        else{
            return profil.getAge();
        }
    }
    public Integer getSexe(){
        if(profil==null){
            return null;
        }
        else{
            return profil.getSexe();
        }
    }
}
