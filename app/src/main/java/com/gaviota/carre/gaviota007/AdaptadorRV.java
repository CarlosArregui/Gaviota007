package com.gaviota.carre.gaviota007;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;
//Comentar esta clase adaptador podria causar cambios en las leyes espacio-temporales de la fisica, asi que no lo hare.
public class AdaptadorRV extends RecyclerView.Adapter<AdaptadorRV.ListaPuntosHolder> implements InterfazClickRV {
    @NonNull
    static List<Evento> lista_eventos_recy;
    private static InterfazClickRV itemListener;
    private View.OnClickListener listener;
    public AdaptadorRV(List<Evento> lista_puntos) {
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
        //listaPuntosHolder.tx_fecha.setText(evento.getFecha());
        //listaPuntosHolder.tx_hora.setText(evento.get);
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
    TextView tx_fecha, tx_hora;
    ImageView imagen;
    int i;

    Button btn_abrir;
    ConstraintLayout const_lay;
        public ListaPuntosHolder(@NonNull View itemView) {
            super(itemView);

            tx_fecha=itemView.findViewById(R.id.tx_fecha);
            tx_hora=itemView.findViewById(R.id.tx_hora);
            imagen = itemView.findViewById(R.id.imagen);

            const_lay=(ConstraintLayout)itemView.findViewById(R.id.constraint_lay);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            sacarAlertDialog(lista_eventos_recy.get(this.getPosition()), v );

        }
    }
    public static void sacarAlertDialog(final Evento evento, View v) {
        // con este tema personalizado evitamos los bordes por defecto
        Dialog customDialog = new Dialog(v.getContext(),R.style.Theme_Dialog_Translucent);
        //deshabilitamos el título por defecto
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //obligamos al usuario a pulsar los botones para cerrarlo
        customDialog.setCancelable(false);
        //establecemos el contenido de nuestro dialog
        customDialog.setContentView(R.layout.alert_di_recy);

        TextView tx_titulo = customDialog.findViewById(R.id.alert_titulo);
        TextView tx_fecha = customDialog.findViewById(R.id.alert_fecha);
        TextView tx_hora = customDialog.findViewById(R.id.alert_hora);
        TextView tx_descripcion = customDialog.findViewById(R.id.alert_descripcion);
        ImageView imagen = customDialog.findViewById(R.id.alert_imagen);
        Button btn_mapa = customDialog.findViewById(R.id.btn_mapa);

        View.OnClickListener mapa = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), SacaPuntos.class).putExtra("localizacion",evento.getLocalizacion()));

            }
        };
        btn_mapa.setOnClickListener(mapa);


        ((Button) customDialog.findViewById(R.id.btn_aceptar)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {

                }
        });

        ((Button) customDialog.findViewById(R.id.btn_cancelar)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {

            }
        });
        customDialog.show();
    }
}
