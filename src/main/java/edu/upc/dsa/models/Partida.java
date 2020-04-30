package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Partida {
    String id;
    String idusuario;
    int puntuacion;
    SimpleDateFormat fechainicio;
    SimpleDateFormat fechafinal;
    Nivel nivel;
    ArrayList<Objeto> equipamiento;

    public Partida() {
        this.id = RandomUtils.getId();
    }

    public Partida(String id, String idusuario) {
        this();
        this.id = id;
        this.idusuario = idusuario;
        this.equipamiento = new ArrayList<Objeto>();

    }

    public ArrayList<Objeto> getEquipamiento() {
        return equipamiento;
    }

    public void setEquipamiento(ArrayList<Objeto> equipamiento) {
        this.equipamiento = equipamiento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public SimpleDateFormat getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(SimpleDateFormat fechainicio) {
        this.fechainicio = fechainicio;
    }

    public SimpleDateFormat getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(SimpleDateFormat fechafinal) {
        this.fechafinal = fechafinal;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
}
