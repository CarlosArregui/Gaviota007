package com.gaviota.carre.gaviota007;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GuardadosFragment extends Fragment {
    RecyclerView rv;
    List<Evento> eventos;
    AdaptadorRV adapter;
    Context contexto;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        rv = getView().findViewById(R.id.recycler_home);
        rv.setLayoutManager(new LinearLayoutManager(contexto));
        eventos = new ArrayList<>();
        sacarDatosUsers();

    }

    public  void sacarDatosUsers(){

        DatabaseReference bbdd =FirebaseDatabase.getInstance().getReference("usuarios");


        // queremos ver los eventos creados del usuario Pepe
        Query q= bbdd.child("jorge").child("eventos_guardados");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){


                    ArrayList<String>lista_ids=new ArrayList<>();
                    for (DataSnapshot d : dataSnapshot.getChildren()){

                        String id=d.getKey();
                        lista_ids.add(id);
                        Log.v("datos",id);


                    }
                    sacarEventosporID(lista_ids);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    public void sacarEventosporID(ArrayList<String> lista_ids){

        //por cada evento haremos una peticion a a bbdd
        for (String id : lista_ids){
            Query q= FirebaseDatabase.getInstance().getReference("eventos").orderByKey().equalTo(id);
            q.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        for (DataSnapshot d : dataSnapshot.getChildren()){
                            Evento e=d.getValue(Evento.class);
                            Log.v("datos",e.getTitulo());
                            eventos.add(e);


                        }
                        adapter = new AdaptadorRV(eventos);
                        adapter.notifyDataSetChanged();
                        rv.setAdapter(adapter);

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}
