package com.gaviota.carre.gaviota007;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        //Splash Screen
        ImageView imagen = findViewById(R.id.imagen);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.gaviotaanim);

        imagen.startAnimation(myanim);
        imagen.setVisibility(imagen.INVISIBLE);

        openApp(true);
    }

    private void openApp(boolean locationPermission) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen
                        .this, ActivityLogin.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
