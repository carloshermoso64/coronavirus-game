package edu.upc.dsa.models;

import org.apache.commons.lang3.RandomStringUtils;

public class Token {
    String id;
    String idUser;
    String admin;

    public Token(String idUser, String admin) {
        this.id = RandomStringUtils.randomAlphanumeric(16);
        this.idUser = idUser;
        this.admin = admin;
    }

    public Token() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}