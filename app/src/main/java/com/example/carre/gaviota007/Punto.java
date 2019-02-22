package com.example.carre.gaviota007;

public class Punto {
    private String creador, localizacion;

    public Punto(String creador, String tipo) {
        this.creador = creador;
        this.localizacion = tipo;
    }
    public Punto(){

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
