package com.example.carre.gaviota007.Evento;

import java.util.ArrayList;

public class Usuario {
    String correo;
    String contraseña;
    String nombre_usuario;
    ArrayList<String> evento_creados;
    ArrayList<String> evento_guardados;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public ArrayList<String> getEvento_creados() {
        return evento_creados;
    }

    public void setEvento_creados(ArrayList<String> evento_creados) {
        this.evento_creados = evento_creados;
    }

    public ArrayList<String> getEvento_guardados() {
        return evento_guardados;
    }

    public void setEvento_guardados(ArrayList<String> evento_guardados) {
        this.evento_guardados = evento_guardados;
    }
}
