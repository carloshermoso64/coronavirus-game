package edu.upc.dsa.models;

import org.apache.commons.lang3.RandomStringUtils;

public class ShopItem {

    private String id;
    private String name;
    private String description;
    private int cost;

    public ShopItem() {
    }

    public ShopItem(String name, String description, int cost) {
        this.id = RandomStringUtils.randomAlphanumeric(8);
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}


