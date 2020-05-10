package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class User implements Comparable<User>{
    private String id;
    private String name;
    private String email;
    private String password;
    private int exp;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = RandomStringUtils.randomAlphanumeric(8);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getExp() {
        return exp;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public int compareTo(User o) {
        String nombre;
        return this.name.compareTo(o.getName());
    }
}
