package com.BomiGael.exam_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Page_Connexion extends AppCompatActivity {

    private EditText edConnEmail, edConnPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_connexion);

        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        Button btnConect = findViewById(R.id.Auth);
        this.edConnEmail = findViewById(R.id.connEmail);
        this.edConnPassword = findViewById(R.id.connPassword);

        btnConect.setOnClickListener(view -> connexion());
    }


    //Méthode pour la connexion d'un utilisateur
    private void connexion(){
        String email = edConnEmail.getText().toString().trim();
        String passWord = edConnPassword.getText().toString().trim();

        try {
            mAuth.signInWithEmailAndPassword(email, passWord).addOnCompleteListener(task -> {

                if (task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), ListeUser.class));
                    Toast.makeText(Page_Connexion.this,"Connexion réussie", Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(Page_Connexion.this,"La connexion a échouer !\n Vérifiez vos" +
                            " informations.", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}