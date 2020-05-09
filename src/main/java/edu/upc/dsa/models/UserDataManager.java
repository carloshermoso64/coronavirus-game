package edu.upc.dsa.models;

public class UserDataManager {
    private String name;
    private String email;
    private String password;
    private String oldname;

    public UserDataManager() {
    }

    public UserDataManager(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserDataManager(String name, String email, String password, String oldname) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.oldname = oldname;
    }

    public String getOldname() {
        return oldname;
    }

    public void setOldname(String oldname) {
        this.oldname = oldname;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
