package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.ArrayList;

public class Nivel {
    String id;
    ArrayList<Mapa> mapas;

    public Nivel() {
        this.id = RandomUtils.getId();
        this.mapas = new ArrayList<Mapa>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Mapa> getMapas() {
        return mapas;
    }

    public void setMapas(ArrayList<Mapa> mapas) {
        this.mapas = mapas;
    }
}
