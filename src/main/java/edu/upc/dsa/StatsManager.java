package edu.upc.dsa;

import edu.upc.dsa.models.Stats;

import java.util.List;

public interface StatsManager {


    public Stats addStats(int puntuacion, int dias, int salud, int alimentos, int entretenimiento);
    public Stats addStats(Stats s);
    public Stats getStats(String id);
    public List<Stats> findAll();
    public void deleteStats(String id);
    public Stats updateStats(Stats s);

    public int size();
}
