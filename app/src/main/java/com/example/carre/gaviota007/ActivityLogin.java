package com.example.carre.gaviota007;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }
}
