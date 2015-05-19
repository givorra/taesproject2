package com.project2.taes.farmacia;

import java.io.Serializable;

/**
 * Created by Andrea on 19/05/2015.
 */
public class Medico implements Serializable
{
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected int id;
    protected String nombre;
    protected String n_colegiado;
    protected String foto;

    public Medico(int id, String nombre, String n_colegiado, String foto) {
        this.nombre = nombre;
        this.n_colegiado = n_colegiado;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getN_colegiado() {
        return n_colegiado;
    }

    public void setN_colegiado(String n_colegiado) {
        this.n_colegiado = n_colegiado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
