package com.example.carre.gaviota007;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityLogin extends AppCompatActivity {
    Button sign_up,log_in;
    private FirebaseAuth mAuth;
    private EditText emailLog;
    private EditText passLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        emailLog=(EditText)findViewById(R.id.etLog_email);
        passLog=(EditText)findViewById(R.id.etLog_pass);
        sign_up =(Button)findViewById(R.id.sign_up);
        log_in=(Button)findViewById(R.id.log_in);
        final Intent I = new Intent(this,Registro.class);
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogearUsuario();
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(I);
            }
        });
    }
    private void LogearUsuario() {
        String correo=emailLog.getText().toString().trim();
        String contra=passLog.getText().toString().trim();
        if(TextUtils.isEmpty(correo)){
            Toast.makeText(this,"Se debe de ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(contra)){
            Toast.makeText(this,"Se debe de ingresar una contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(ActivityLogin.this, "Se ha iniciado sesión", Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(ActivityLogin.this, "No has ingresado bien los datos.", Toast.LENGTH_LONG).show();
                            //updateUI(null);
                        }



                    }
                });
    }



}
