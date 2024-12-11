package com.example.tp00.modele;

import junit.framework.TestCase;

public class ProfilTest extends TestCase {


private Profil profil=new Profil(67,165,35,0);
private float img=(float) 32.2;
private String message="trop élevé";


    public void testGetMessage() {
        assertEquals(message,profil.getMessage());
    }

    public void testGetImg() {
        assertEquals(img,profil.getImg(),(float) 0.1);
    }
}