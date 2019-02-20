package com.example.carre.gaviota007;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;


import com.example.carre.gaviota007.AdaptadorRV;
import com.example.carre.gaviota007.Punto;
import com.example.carre.gaviota007.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewIncial extends AppCompatActivity {
//Pantalla inicial Listado
    RecyclerView rv;
    List<Punto> puntos;
    AdaptadorRV adapter;
    Context contexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy_view_inicial);
        rv= findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        puntos= new ArrayList<>();
        FirebaseDatabase firebase= FirebaseDatabase.getInstance();


        firebase.getReference().child("eventos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                puntos.removeAll(puntos);
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Punto punto=snapshot.getValue(Punto.class);
                    puntos.add(punto);
                }
                adapter= new AdaptadorRV(puntos);
                adapter.notifyDataSetChanged();
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
