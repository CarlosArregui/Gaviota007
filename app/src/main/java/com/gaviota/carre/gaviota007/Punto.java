package com.gaviota.carre.gaviota007;

public class Punto {
    private String creador, localizacion, id;

    public Punto() {
    }

    public Punto(String creador, String localizacion, String id) {
        this.creador = creador;
        this.localizacion = localizacion;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
