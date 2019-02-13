package com.example.carre.gaviota007.Usuario.RecyclerVieww;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carre.gaviota007.Punto;
import com.example.carre.gaviota007.R;

import java.util.List;

public class AdaptadorRV extends RecyclerView.Adapter<AdaptadorRV.ListaPuntosHolder> implements View.OnClickListener{
    @NonNull
    List<Punto> lista_puntos_recy;

    private View.OnClickListener listener;
    public AdaptadorRV(List<Punto> lista_puntos) {
        this.lista_puntos_recy=lista_puntos;
    }


    @Override
    public ListaPuntosHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_vista_add,viewGroup, false);
        v.setOnClickListener(this);
        ListaPuntosHolder puntos = new ListaPuntosHolder(v);
        return puntos;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPuntosHolder listaPuntosHolder, int i) {
        Punto punto =lista_puntos_recy.get(i);
        listaPuntosHolder.tv_creador.setText(punto.getCreador());
        listaPuntosHolder.tv_tipo.setText(punto.getTipo());
    }

    @Override
    public int getItemCount() {
        return lista_puntos_recy.size();
    }

    public void setOnCLickListener(View.OnClickListener listener){
        this.listener=listener;
}
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public static class ListaPuntosHolder extends RecyclerView.ViewHolder  {
    TextView tv_creador, tv_tipo;
    ImageView iv_imagen;

        public ListaPuntosHolder(@NonNull View itemView) {
            super(itemView);

            tv_creador=itemView.findViewById(R.id.tv_recy_creador);
            tv_tipo=itemView.findViewById(R.id.tv_recy_tipo);

        }
    }
}
