package com.example.carre.gaviota007;

public class puntos {
    String creador,localizacion;

    public puntos(String creador, String localizacion) {
        this.creador = creador;
        this.localizacion = localizacion;
    }

    public puntos() {
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
}
