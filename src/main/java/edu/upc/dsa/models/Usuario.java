package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Usuario {
    String id;
    String nombre;
    String correo;
    String contraseña;
    int experiencia;

    public Usuario() {
        this.id = RandomUtils.getId();
    }

    public Usuario(String nombre, String correo, String contraseña) {
        this();
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
}
