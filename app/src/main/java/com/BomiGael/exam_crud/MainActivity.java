package com.BomiGael.exam_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button con= findViewById(R.id.btcon);
        Button ins= findViewById(R.id.btins);

        con.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),Page_Connexion.class)));
        ins.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),Page_inscription.class)));
    }
}