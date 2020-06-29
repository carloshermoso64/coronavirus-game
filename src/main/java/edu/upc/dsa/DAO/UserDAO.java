package edu.upc.dsa.DAO;

import edu.upc.dsa.models.User;

import java.util.ArrayList;

public interface UserDAO {

    public String addUser(String name, String email, String password);
    public User getUser(String searchField, String value);
    public User updateUser(User newUser);
    public String checkLogin(String name, String password);
    public User getUserByName(String name);
    public boolean deleteUser(User user);
    public ArrayList<User> getAllUsers();

}
