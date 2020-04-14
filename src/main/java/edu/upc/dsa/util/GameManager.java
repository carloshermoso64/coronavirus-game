package edu.upc.dsa.util;

import edu.upc.dsa.models.User;
import edu.upc.dsa.models.Objeto;
import java.util.List;

public interface GameManager {
    public List<User> getUsers();
    public void addUser(String name, String lastname,String id);
    public void updateUser(String name, String lastname, String mail, String id);
    public int getNumUsers();
    public User getUser(String iduser);
    public void addObjectUser(String iduser, String idobj,String desc);
    public int getnumObjectsbyUser(String iduser);
    public void clear();
}
