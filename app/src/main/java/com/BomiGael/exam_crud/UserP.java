package com.BomiGael.exam_crud;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class UserP extends AppCompatActivity {

    Dialog uptoDatingDialog;
    Button btnValid, btnModifier;
    String userUID;
    TextView userPhone, userMail, userName, userPass;

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profil);

        FirebaseDatabase.getInstance().getReference();

        this.userMail = findViewById(R.id.userProfilMail);
        this.userName = findViewById(R.id.userProfilName);
        this.userPhone = findViewById(R.id.userProfilPhone);
        this.userPass = findViewById(R.id.userProfilPass);

        userName.setText("Nom et prenom: "+Adapter.strShowName);
        userPhone.setText("Numéro de téléphone: "+Adapter.strShowPhone);
        userMail.setText("E-mail: "+Adapter.strShowMail);
        userPass.setText("Mot de passe: "+Adapter.strShowPass);

        uptoDatingDialog = new Dialog(this);
        uptoDatingDialog.setContentView(R.layout.update);
        uptoDatingDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        uptoDatingDialog.getWindow().setGravity(Gravity.CENTER);
        uptoDatingDialog.setCanceledOnTouchOutside(false);

        final EditText editNameU = uptoDatingDialog.findViewById(R.id.editTxtUpDateName);
        final EditText editMailU = uptoDatingDialog.findViewById(R.id.editTxtUpDateEmail);
        final EditText editPhoneU = uptoDatingDialog.findViewById(R.id.editTxtUpDatePhone);
        this.btnValid = uptoDatingDialog.findViewById(R.id.btnValidUpDating);
        btnValid.setOnClickListener(view -> {
            String newName = editNameU.getText().toString();
            String newMail = editMailU.getText().toString();
            String newPhone = editPhoneU.getText().toString();
            try {
                upToDate(newName, newMail, newPhone);
            } catch (Exception e) {
                e.printStackTrace();
            }
            uptoDatingDialog.dismiss();
        });

        this.btnModifier = findViewById(R.id.btnModifier);
        btnModifier.setOnClickListener(view -> {
            editNameU.setText(Adapter.strShowName);
            editPhoneU.setText(Adapter.strShowPhone);
            editMailU.setText(Adapter.strShowMail);
            uptoDatingDialog.show();
        });

        Button btnDelete = findViewById(R.id.btnDel);
        btnDelete.setOnClickListener(view -> deleteUser(Adapter.strShowMail, Adapter.strShowPass));

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        userUID = Objects.requireNonNull(currentFirebaseUser).getUid();
    }


    public void deleteUser(String email, String password){

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(email, password);

        // Prompt the user to re-provide their sign-in credentials
        if (user != null) {
            user.reauthenticate(credential).addOnCompleteListener(task ->
                    user.delete().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {

                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users_Gael");
                            mDatabase.child(userUID).removeValue().addOnCompleteListener(task2 -> {});

                            Log.d("TAG", "User account deleted.");
                            startActivity(new Intent(UserP.this, MainActivity.class));
                            Toast.makeText(UserP.this, "Deleted User Successfully,", Toast.LENGTH_LONG).show();
                        }
                    }));
        }



    }

    @SuppressLint("SetTextI18n")
    public  void upToDate(String name, String mail, String phone){

        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("nom",name);
        userMap.put("mail",mail);
        userMap.put("contact",phone);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users_Gael");
        mDatabase.child(userUID).updateChildren(userMap).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Adapter.strShowName = name;
                Adapter.strShowMail = mail;
                Adapter.strShowPhone = phone;

                userName.setText("Nom et prenom: "+Adapter.strShowName);
                userPhone.setText("Numéro de téléphone: "+Adapter.strShowPhone);
                userMail.setText("E-mail: "+Adapter.strShowMail);
                userPass.setText("Mot de passe: "+Adapter.strShowPass);

                Toast.makeText(getApplicationContext(),"Informations modifiées avec succès!",
                        Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Une erreur s'est produite",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}