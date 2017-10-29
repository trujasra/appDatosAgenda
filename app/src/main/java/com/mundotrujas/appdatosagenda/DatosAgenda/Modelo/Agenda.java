package com.mundotrujas.appdatosagenda.DatosAgenda.Modelo;

/**
 * Created by ramiro.trujillo on 23/10/2017.
 */

public class Agenda {

    private int _id;
    private int id_usuario;
    private String titulo;
    private String descripcion;
    private int estado;

    public Agenda() {
    }

    public Agenda(int _id, int id_usuario, String titulo, String descripcion, int estado) {
        this._id = _id;
        this.id_usuario = id_usuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
