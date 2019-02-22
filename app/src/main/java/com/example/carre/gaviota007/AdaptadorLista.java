package com.example.carre.gaviota007;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;



import java.util.ArrayList;

public class AdaptadorLista extends BaseAdapter {
    ArrayList<Evento> lista_evento;
    Context contexto;

    public AdaptadorLista(ArrayList<Evento> lista_madrid, Context contexto) {
        this.lista_evento = lista_madrid;
        
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return lista_evento.size();
    }

    @Override
    public Object getItem(int position) {
        return lista_evento.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(contexto);
        View vista= inflater.inflate(R.layout.celdas, parent, false);
        vista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarAlert(lista_evento.get(position).getDescripcion());
            }
        });
        return vista;
    }
    private void generarAlert(String descripcion) {
        AlertDialog.Builder constructor = new AlertDialog.Builder(contexto);
        constructor.setTitle("Descripcion");
        LayoutInflater inflador = LayoutInflater.from(contexto);
        final View vista2 = inflador.inflate(R.layout.vista_alert,null);
        constructor.setView(vista2);
        constructor.setNegativeButton("Salir", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alert=constructor.create();
        alert.show();
    }
}
