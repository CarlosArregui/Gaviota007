package com.gaviota.carre.gaviota007;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class AlternativeFragment extends Fragment {
    private   Button btnCerrar, btnEmail, btnContra;
    private  RecyclerView rv;
    private  List<Evento> eventos;
    private  AdaptadorRV adapter;
    AlertDialog dialogo;
    private FirebaseAuth mAuth;
    Context contexto;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alternative, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        btnCerrar=getActivity().findViewById(R.id.btn_Cerrar);
        btnEmail=getActivity().findViewById(R.id.btn_cambioContra);
        btnContra=getActivity().findViewById(R.id.btn_cambioEmail);
        mAuth = FirebaseAuth.getInstance();
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });
        btnContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambioContraseña();
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Borrar();
            }
        });
    }
//Borra la cuenta de firebase y te lleva al login.
    private void Borrar() {
        FirebaseUser user=mAuth.getCurrentUser();
        user.delete();
        final Intent loging = new Intent(contexto, ActivityLogin.class);
        startActivity(loging);

    }
    //cierra sesión de la cuenta de firebase y te lleva al login.
    private void cerrarSesion() {
        mAuth.getInstance().signOut();
        final Intent loging = new Intent(contexto, ActivityLogin.class);
        startActivity(loging);
    }
    //cambio dez sesión de la cuenta de firebase y te lleva al login.
    private void cambioContraseña() {

        AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=this.getLayoutInflater();
          dialogo = dialog.create();
        final View dialogView=inflater.inflate(R.layout.alert_dialog_cambiocontrasena, null);
        dialog.setView(dialogView);
        final EditText editText_passForget=(EditText)dialogView.findViewById(R.id.et_insertContra);

        final Button boton_enviarforget=(Button)dialogView.findViewById(R.id.btn_enviarpass);
        boton_enviarforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contrachange=editText_passForget.getText().toString().trim();
                FirebaseUser user=mAuth.getCurrentUser();
                try {
                    user.updatePassword(contrachange);
                    Toast.makeText(getActivity(),"Cambiada",Toast.LENGTH_LONG);
                    final Intent loging = new Intent(contexto, ActivityLogin.class);
                    startActivity(loging);
                }catch (Exception e){
                    Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG);
                }


            }
        });


        dialog.show();
    }



}
