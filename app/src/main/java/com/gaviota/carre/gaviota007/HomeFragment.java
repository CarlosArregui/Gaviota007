package com.gaviota.carre.gaviota007;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView rv;
    List<Evento> eventos;
    AdaptadorRV adapter;
    Context contexto;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_principal, container, false);

    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        rv = getView().findViewById(R.id.recycler_);
        rv.setLayoutManager(new LinearLayoutManager(contexto));
        eventos = new ArrayList<>();
        FirebaseDatabase firebase = FirebaseDatabase.getInstance();


        firebase.getReference().child("eventos").addValueEventListener(new ValueEventListener() {
            @Override
            //saca datos y los catualiza en la vista
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventos.removeAll(eventos);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Evento evento = snapshot.getValue(Evento.class);
                    eventos.add(evento);
                }
                adapter = new AdaptadorRV(eventos);
                adapter.notifyDataSetChanged();
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}