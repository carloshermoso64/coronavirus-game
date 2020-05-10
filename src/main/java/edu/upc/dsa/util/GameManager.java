package edu.upc.dsa.util;

import edu.upc.dsa.models.User;

import java.util.HashMap;
import java.util.List;

public interface GameManager {
    public HashMap<String, User> getUsers();
    public List<User> getUsersList();
    public void addUser(String name, String lastname,String id);
    public void addUser(User user);
    public void updateUser(String name, String mail, String id);
    public User getUser(String iduser);
    public int getNumUsers();
    public void clear();
}
