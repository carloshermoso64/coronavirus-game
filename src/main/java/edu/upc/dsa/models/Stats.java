package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Stats {

    String id;
    int puntuacion;
    int dias;
    int salud;
    int alimentos;
    int entretenimiento;
    static int lastId;

    public Stats() {
        this.id = RandomUtils.getId();
    }

    public Stats(int puntuacion, int dias, int salud, int alimentos, int entretenimiento) {
        this();
        this.setPuntuacion(puntuacion);
        this.setDias(dias);
        this.setSalud(salud);
        this.setAlimentos(alimentos);
        this.setEntretenimiento(entretenimiento);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }


    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(int alimentos) {
        this.alimentos = alimentos;
    }

    public int getEntretenimiento() {
        return entretenimiento;
    }

    public void setEntretenimiento(int entretenimiento) {
        this.entretenimiento = entretenimiento;
    }


    @Override
    public String toString() {
        return "Stats [id="+id+", puntuacion final=" + puntuacion + ", dias en cuarentena=" + dias +"," +
                " nivel de salud=" + salud + ", numero de alimentos=" + alimentos + ", nivel de entretenimiento=" + entretenimiento + "]";
    }

}