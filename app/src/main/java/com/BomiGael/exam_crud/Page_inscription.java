package com.BomiGael.exam_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Page_inscription extends AppCompatActivity {

    private EditText edName, edPhone, edMail, edPasswrd;
    private String strName, strPhone, strMail, strPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_inscription);

        mAuth = FirebaseAuth.getInstance();

        this.edName = findViewById(R.id.edName);
        this.edPhone =findViewById(R.id.edPhone);
        this.edMail = findViewById(R.id.edEmail);
        this.edPasswrd = findViewById(R.id.edPassword);

        Button btnInscription = findViewById(R.id.SignIn);
        btnInscription.setOnClickListener(view -> inscription());
    }




    public void inscription(){
        strName = edName.getText().toString().trim();
        strPhone = edPhone.getText().toString().trim();
        strMail = edMail.getText().toString().trim();
        strPass = edPasswrd.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(strMail, strPass).
                addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Users agent = new Users(strName, strMail, strPhone, strPass);

                        FirebaseDatabase.getInstance().getReference("Users_Gael")
                                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).
                                        getUid()).setValue(agent).addOnCompleteListener(task1 -> {
                                    if(task1.isSuccessful()){
                                        Toast.makeText(Page_inscription.this,"Inscription réussi !",
                                                Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Page_inscription.this, ListeUser.class));
                                    }
                                    else {
                                        Toast.makeText(Page_inscription.this,"Votre inscription n'a" +
                                                        " pas été fait !\n Essayez à nouveau",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                    }else {
                        Toast.makeText(Page_inscription.this,"Votre inscription n'a" +
                                " pas été fait !\n Essayez à nouveau", Toast.LENGTH_LONG).show();
                    }
                });

    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }
}