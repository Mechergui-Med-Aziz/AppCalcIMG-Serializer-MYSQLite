package com.example.tp00.modele;

import java.io.Serializable;
import java.util.Date;

public class Profil  implements Serializable {

    private static final Integer minFemme=15;
    private static final Integer minHomme=10;
    private static final Integer maxFemme=30;
    private static final Integer maxHomme=25;
    private Date dateMesure;
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float img;
    private String message;

    public Profil(Date dateMesure ,Integer poids, Integer taille, Integer age, Integer sexe) {
        this.dateMesure = dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculIMG();
        this.resultIMG();
    }

    public Integer getPoids() {
        return poids;
    }

    public String getMessage() {
        return message;
    }

    public float getImg() {
        return img;
    }

    public Integer getSexe() {
        return sexe;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getTaille() {
        return taille;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    private void calculIMG(){
        float tailleM=(float)getTaille()/100;
        this.img= (float) ((1.2*getPoids()/(tailleM*tailleM))+(0.23*getAge())-(10.83*getSexe())-5.4);
    }
    private void resultIMG(){
        Integer min;
        Integer max;
        if(getSexe()==0){
            min=minFemme;
            max=maxFemme;
        }else{
            min=minHomme;
            max=maxHomme;
        }
        message="normal";
        if(getImg()<min){
            message="trop faible";
        } else if (getImg()>max) {
            message="trop élevé";
        }
    }
}
