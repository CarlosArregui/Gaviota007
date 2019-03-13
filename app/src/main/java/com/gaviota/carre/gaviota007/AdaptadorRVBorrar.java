package com.gaviota.carre.gaviota007;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
//Comentar esta clase adaptador podria causar cambios en las leyes espacio-temporales de la fisica, asi que no lo hare.
public class AdaptadorRVBorrar extends RecyclerView.Adapter<AdaptadorRVBorrar.ListaPuntosHolder> implements InterfazClickRV {
    @NonNull
    static List<Evento> lista_eventos_recy;
    static String id;
    Context contexto;
    private static InterfazClickRV itemListener;
    private View.OnClickListener listener;
    public AdaptadorRVBorrar(List<Evento> lista_puntos) {
        this.lista_eventos_recy=lista_puntos;
    }


    @Override
    public ListaPuntosHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_vista_add,viewGroup, false);

        // viewGroup.setOnClickListener(this);
        ListaPuntosHolder puntos = new ListaPuntosHolder(v);
        return puntos;
    }
    View.OnClickListener oyente=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    @Override
    public void onBindViewHolder(@NonNull ListaPuntosHolder listaPuntosHolder, int i) {
        Evento evento =lista_eventos_recy.get(i);
        listaPuntosHolder.tv_titulo_re.setText(evento.getTitulo());
        listaPuntosHolder.tv_fecha.setText(evento.getFecha());
        if(evento.getTipo().equalsIgnoreCase("medusas")){
            listaPuntosHolder.imagen.setImageResource(R.drawable.medusa);
        } else if(evento.getTipo().equalsIgnoreCase("limpiar")){
            listaPuntosHolder.imagen.setImageResource(R.drawable.papelera);
        } else if(evento.getTipo().equalsIgnoreCase("chorizos")){
            listaPuntosHolder.imagen.setImageResource(R.drawable.ladron);
        }
        listaPuntosHolder.i=i;
        // listaPuntosHolder.const_lay.setOnClickListener(oyente);
    }

    @Override
    public int getItemCount() {
        return lista_eventos_recy.size();
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }


    public static class ListaPuntosHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_titulo_re, tv_fecha;
        ImageView imagen;
        int i;

        Button btn_abrir;
        ConstraintLayout const_lay;
        public ListaPuntosHolder(@NonNull View itemView) {
            super(itemView);
            tv_titulo_re=itemView.findViewById(R.id.tv_recy_titulo);
            tv_fecha=itemView.findViewById(R.id.tv_recy_fecha);
            imagen=itemView.findViewById(R.id.imagen);
            const_lay=(ConstraintLayout)itemView.findViewById(R.id.constraint_lay);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            sacarAlertDialog(lista_eventos_recy.get(this.getPosition()), v );

        }
    }
    public static void sacarAlertDialog(final Evento evento2, View v)
    {
        // Log.v("clicado", "posciion:"+position);

        // Evento evento=lista_eventos_recy.get(position);

        Log.v("clicado","Clase:"+ v.getClass());
        AlertDialog.Builder constructor= new AlertDialog.Builder(v.getContext());
        constructor.setTitle(evento2.getTitulo());
        LayoutInflater inflador=LayoutInflater.from(v.getContext());
        final View vista=inflador.inflate(R.layout.alert_di_recy,null);
        constructor.setView(vista);

        TextView tv_creador= vista.findViewById(R.id.tv_creador);

        TextView tv_fecha_hora= vista.findViewById(R.id.tv_fecha_hora);
        TextView tv_descripcion= vista.findViewById(R.id.tv_desc);
        id= evento2.getId();

        tv_creador.setText(evento2.getCreador());
        String participantes= Integer.toString(evento2.getParticipantes());

        tv_fecha_hora.setText(evento2.getFecha());
        tv_descripcion.setText(evento2.getDescripcion());

        constructor.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("ALERT","has clicado aceptar");
            }
        });
        constructor.setNegativeButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("dato",evento2.getId());
                DatabaseReference bbdd= FirebaseDatabase.getInstance().getReference("eventos").child(evento2.getId());
                bbdd.removeValue();
                
            }
        });
        AlertDialog alert=constructor.create();
        alert.show();
    }


}