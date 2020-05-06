package edu.upc.dsa.util;

import edu.upc.dsa.models.Usuario;

import java.util.HashMap;
import java.util.List;

public interface GameManager {
    public HashMap<String,Usuario> getUsers();
    public List<Usuario> getUsersList();
    public void addUser(String name, String lastname,String id);
    public void addUser(Usuario usuario);
    public void updateUser(String name, String mail, String id);
    public Usuario getUser(String iduser);
    public int getNumUsers();
    public void clear();
}
