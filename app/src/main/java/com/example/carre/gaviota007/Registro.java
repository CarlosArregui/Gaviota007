package com.example.carre.gaviota007;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Paantalla de registro
        setContentView(R.layout.activity_registro);
        getSupportActionBar().hide();
    }
}
