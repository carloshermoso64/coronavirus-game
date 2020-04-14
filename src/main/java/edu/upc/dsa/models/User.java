package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User implements Comparable<User>{
    String id;
    String name;
    String lastname;
    String mail;
    LinkedList<Objeto> objects;

    public User(String n, String ln, String id){
        this.name = n;
        this.lastname = ln;
        this.objects = new LinkedList<Objeto>();
        this.id = id;
    }

    public User(){
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getId() {
        return id;
    }

    public LinkedList<Objeto> getObjects() {
        return objects;
    }

    public void setObjects(LinkedList<Objeto> objects) {
        this.objects = objects;
    }

    public void addObject(Objeto obj){ this.objects.add(obj); }

    public void setMail(String bio) {
        this.mail = bio;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public int compareTo(User u) {
        if (getLastname() == null || u.getLastname() == null) {
            return 0;
        }
        return getLastname().compareTo(u.getLastname());
    }
}
