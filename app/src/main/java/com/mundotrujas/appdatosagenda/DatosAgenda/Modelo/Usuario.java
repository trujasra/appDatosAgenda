package com.mundotrujas.appdatosagenda.DatosAgenda.Modelo;

/**
 * Created by ramiro.trujillo on 23/10/2017.
 */

public class Usuario {
    private int _id;
    private String nombres;
    private String primer_apellido;
    private String segundo_apellido;
    private String usuario;
    private String password;
    private int estado;

    public Usuario() {
    }

    public Usuario(int _id, String nombres, String primer_apellido, String segundo_apellido, String usuario, String password, int estado) {
        this._id = _id;
        this.nombres = nombres;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.usuario = usuario;
        this.password = password;
        this.estado = estado;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
