package edu.upc.dsa.models;


public class UserTO {
    private String name;
    private String email;
    private String id;
    private int exp;
    private int level;

    public UserTO() {
    }


    public UserTO(String name, String email, String id, int level) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
        this.level = u.getLevel();
    }
}
