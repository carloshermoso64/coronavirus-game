package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Personaje {
    String id;
    String nombre;
    Integer hp;
    String clase;

    public Personaje() {
        this.id = RandomUtils.getId();
    }

    public Personaje(String nombre, Integer hp, String clase) {
        this();
        this.nombre = nombre;
        this.hp = hp;
        this.clase = clase;
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

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
}
