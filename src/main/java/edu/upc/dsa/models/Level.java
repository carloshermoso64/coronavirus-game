package edu.upc.dsa.models;

import org.apache.commons.lang3.RandomStringUtils;

public class Level {
    private String id;
    private String map;
    private int levelNumber;

    public Level() {

    }

    public Level(String map, int levelNumber) {
        this.id = RandomStringUtils.randomAlphanumeric(8);
        this.map = map;
        this.levelNumber = levelNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
}
