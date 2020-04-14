package edu.upc.dsa.models;

public class Objeto {
    String id;
    String descrp;

    public Objeto(String idd, String d){
        this.id = idd;
        this.descrp = d;
    }

    public Objeto(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescrp() {
        return descrp;
    }

    public void setDescrp(String descrp) {
        this.descrp = descrp;
    }
}

