package edu.upc.dsa.models;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;


public class BestLevel {
    private String id;
    private int levelNumber;
    private int bestScore;
    private int bestTime;
    private Date startDate;
    private String idUser;


    public BestLevel() {
    }

    public BestLevel(int levelNumber, int bestScore, int bestTime, String idUser) {
        this.id = RandomStringUtils.randomAlphanumeric(8);
        this.levelNumber = levelNumber;
        this.bestScore = bestScore;
        this.bestTime = bestTime;
        this.idUser = idUser;
        this.startDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public int getBestTime() {
        return bestTime;
    }

    public void setBestTime(int bestTime) {
        this.bestTime = bestTime;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startTime) {
        this.startDate = startTime;
    }
}
