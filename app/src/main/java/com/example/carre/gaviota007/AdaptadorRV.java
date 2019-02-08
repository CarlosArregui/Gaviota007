package com.example.carre.gaviota007;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorRV extends RecyclerView.Adapter<AdaptadorRV.ListaPuntosHolder>{
    @NonNull
    List<Punto> lista_puntos_recy;

    public AdaptadorRV(List<Punto> lista_puntos) {
        this.lista_puntos_recy=lista_puntos;
    }


    @Override
    public ListaPuntosHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_vista_add,viewGroup, false);
        ListaPuntosHolder puntos = new ListaPuntosHolder(v);
        return puntos;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPuntosHolder listaPuntosHolder, int i) {
        Punto punto =lista_puntos_recy.get(i);
        listaPuntosHolder.tv_nombre.setText(punto.getNombre());
        listaPuntosHolder.tv_fecha.setText(punto.getFecha());
    }

    @Override
    public int getItemCount() {
        return lista_puntos_recy.size();
    }

    public static class ListaPuntosHolder extends RecyclerView.ViewHolder{
    TextView tv_nombre, tv_fecha;
    ImageView iv_imagen;

        public ListaPuntosHolder(@NonNull View itemView) {
            super(itemView);
            tv_nombre=itemView.findViewById(R.id.tv_recy_nombre);
            tv_fecha=itemView.findViewById(R.id.tv_recy_fecha);


        }
    }
}
