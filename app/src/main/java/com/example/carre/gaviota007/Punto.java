package com.example.carre.gaviota007;

public class Punto {
    private String creador, tipo;

    public Punto(String creador, String tipo) {
        this.creador = creador;
        this.tipo = tipo;
    }
    public Punto(){

    }
    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
