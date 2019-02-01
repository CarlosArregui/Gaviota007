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

        sign_up =(Button)findViewById(R.id.sign_up);
        final Intent I = new Intent(this,Registro.class);
        View.OnClickListener listar = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(I);
            }
        };
        sign_up.setOnClickListener(listar);
    }
}
