package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.ArrayList;

public class Mapa {
    String id;
    ArrayList<ArrayList<String>> matrizmapa;

    public Mapa() {
        this.id = RandomUtils.getId();
    }

    public Mapa(ArrayList<ArrayList<String>> matrizmapa) {
        this();
        this.matrizmapa = matrizmapa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<ArrayList<String>> getMatrizmapa() {
        return matrizmapa;
    }

    public void setMatrizmapa(ArrayList<ArrayList<String>> matrizmapa) {
        this.matrizmapa = matrizmapa;
    }
}
