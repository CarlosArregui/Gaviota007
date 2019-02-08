package com.example.carre.gaviota007;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityLogin extends AppCompatActivity {
    Button sign_up,log_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //Apertura ventana registro
        sign_up =(Button)findViewById(R.id.sign_up);
        final Intent I = new Intent(this,Registro.class);
        View.OnClickListener registro = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(I);
            }
        };
        sign_up.setOnClickListener(registro);

        //Apertura ventana principal despues de log in
        log_in =(Button)findViewById(R.id.log_in);
        final Intent log = new Intent(this,RecyclerViewIncial.class);
        View.OnClickListener inicio = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(log);
            }
        };
        log_in.setOnClickListener(inicio);
    }
}
