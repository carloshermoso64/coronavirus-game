package edu.upc.dsa.util;


import edu.upc.dsa.models.User;

public interface UserManager {
    public User getUserByName(String name);
    public User getUserByID(String id);
    public String addUser(User user);
    public User updateUser(String newName,String newPassword, String newEmail , String oldID);
    public boolean deleteUser(String id);
    public String checkLogin(User user);
}
