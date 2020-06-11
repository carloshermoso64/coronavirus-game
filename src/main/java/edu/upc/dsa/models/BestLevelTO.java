package edu.upc.dsa.models;

import java.util.Date;

public class BestLevelTO {

    private String id;
    private int levelNumber;
    private int bestScore;
    private int bestTime;
    private Date startDate;
    private String username;

    public BestLevelTO() {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
