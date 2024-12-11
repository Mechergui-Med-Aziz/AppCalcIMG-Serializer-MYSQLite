package com.example.tp00.vue;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp00.R;
import com.example.tp00.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Controle controle;

    private void init(){
        txtPoids= (EditText) findViewById(R.id.txtPoids);
        txtTaille= (EditText) findViewById(R.id.txtTaille);
        txtAge= (EditText) findViewById(R.id.txtAge);
        rdHomme=(RadioButton) findViewById(R.id.rdHomme);
        rdFemme=(RadioButton) findViewById(R.id.rdFemme);
        lblIMG=(TextView) findViewById(R.id.lblIMG);
        imgSmiley=(ImageView) findViewById(R.id.imgSmiley);
        this.controle=Controle.getInstance(this);
        recupProfil();
        ecouteCalcul();
    }

    private void ecouteCalcul(){
        ((Button) findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
               // Toast.makeText(MainActivity.this,"test",Toast.LENGTH_SHORT).show();
                Integer poids=0;
                Integer taille=0;
                Integer age=0;
                Integer sexe=0;
                try {
                    poids=Integer.parseInt(txtPoids.getText().toString());
                    taille=Integer.parseInt(txtTaille.getText().toString());
                    age=Integer.parseInt(txtAge.getText().toString());
                }catch(Exception e){}
                if(rdHomme.isChecked()){
                    sexe=1;
                }
                if(poids==0 || taille==0 || age==0){
                    Toast.makeText(MainActivity.this,"saisie incorrecte",Toast.LENGTH_SHORT).show();
                }else{
                    afficheResult(poids,taille,age,sexe);
                }

            }
        });

    }
private void afficheResult(Integer poids,Integer taille,Integer age,Integer sexe){
        this.controle.creerProfil(poids,taille,age,sexe,this);
        float img=this.controle.getImg();
        String message=this.controle.getMessage();
    if(message.equals("normal")){
        imgSmiley.setImageResource(R.drawable.happy);
        lblIMG.setTextColor(Color.GREEN);
    } else if (message.equals("trop faible")) {
        imgSmiley.setImageResource(R.drawable.sad);
        lblIMG.setTextColor(Color.RED);
    } else {
        imgSmiley.setImageResource(R.drawable.sad);
        lblIMG.setTextColor(Color.RED);
    }

    lblIMG.setText(String.format("%.1f: IMG %s", img, message));

}
public void recupProfil(){
        if(controle.getPoids() != null){
            txtPoids.setText(controle.getPoids().toString());
            txtTaille.setText(controle.getTaille().toString());
            txtAge.setText(controle.getAge().toString() );
            rdFemme.setChecked(true);
            if(controle.getSexe()==1){
                rdHomme.setChecked(true);
            }
            //simule le click sur le button calcul
            ((Button)findViewById(R.id.btnCalc)).performClick();
        }
}

}