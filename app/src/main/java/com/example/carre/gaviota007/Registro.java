package com.example.carre.gaviota007;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carre.gaviota007.Evento.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Registro extends AppCompatActivity {
    EditText email;
    EditText pass,et_usuario;
    EditText passConfirm;
    Button registro;
    private FirebaseAuth mAuth;
    DatabaseReference bbdd;
    String correo;
    String contra;
    String usuario;
    Context contexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().hide();
        FirebaseApp.initializeApp(this);
        contexto=this;
        mAuth = FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.etEmail);
        pass=(EditText)findViewById(R.id.etPass1);
        registro=(Button)findViewById(R.id.btnRegistrar);
        passConfirm=(EditText)findViewById(R.id.etPassConfirm);
        et_usuario=(EditText)findViewById(R.id.et_usuario);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }
    private void registrarUsuario() {
        //Obtenemos los textos de los editext
         correo=email.getText().toString().trim();
         contra=pass.getText().toString().trim();
         usuario=et_usuario.getText().toString().trim();
        String contraConfirm=passConfirm.getText().toString().trim();
        //Verificacion de que no esten vacias los campos
        if(TextUtils.isEmpty(correo)){
            Toast.makeText(this,"Se debe de ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(contra)){
            Toast.makeText(this,"Se debe de ingresar una contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        if (contra.equals(contraConfirm)){
            //Crear usuario
            mAuth.createUserWithEmailAndPassword(correo, contra)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {


                                    bbdd = FirebaseDatabase.getInstance().getReference("usuarios");
                                    Usuario u=new Usuario(correo,contra);
                                    String clave=usuario;
                                    bbdd.child(clave).setValue(u);
                                    Toast.makeText(Registro.this, "Cuenta creada", Toast.LENGTH_LONG).show();




                            } else {

                                Toast.makeText(Registro.this, "Ha habido un problema.", Toast.LENGTH_LONG).show();
                            }

                        }
                    });

        }else
            Toast.makeText(Registro.this, "Las contraseñas no son iguales", Toast.LENGTH_LONG).show();





    }

}
