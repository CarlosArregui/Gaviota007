package com.gaviota.carre.gaviota007;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreadosFragment extends Fragment {
    RecyclerView rv;
    List<Evento> eventos;
    AdaptadorRV adapter;
    Context contexto;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_creados, container, false);
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        rv = getView().findViewById(R.id.recycler_home);
        rv.setLayoutManager(new LinearLayoutManager(contexto));
        eventos = new ArrayList<>();
        FirebaseDatabase firebase = FirebaseDatabase.getInstance();

    }
}
