package com.example.carre.gaviota007;

public class Evento {

    String localizacion;
    String fecha;
    String titulo;
    String descripcion;
    String creador;
    int participantes;
    String tipo;
    String id;

    public Evento() {
    }

    public Evento(String localizacion, String fecha, String titulo, String descripcion, String creador, int participantes, String tipo, String id) {
        this.localizacion = localizacion;
        this.fecha = fecha;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.creador = creador;
        this.participantes = participantes;
        this.tipo = tipo;
        this.id = id;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public int getParticipantes() {
        return participantes;
    }

    public void setParticipantes(int participantes) {
        this.participantes = participantes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

