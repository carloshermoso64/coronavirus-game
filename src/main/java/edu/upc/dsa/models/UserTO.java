package edu.upc.dsa.models;

import dsa.grupo2.models.User;

public class UserTO {
    private String name;
    private String email;
    private String id;
    private int exp;

    public UserTO() {
    }


    public UserTO(String name, String email, String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setTO(User u) {
        this.name = u.getName();
        this.email = u.getEmail();
        this.id = u.getId();
        this.exp = u.getExp();
    }
}
