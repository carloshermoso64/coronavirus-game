package edu.upc.dsa.util;

import dsa.grupo2.models.User;
import edu.upc.dsa.models.UserTO;

import java.util.HashMap;
import java.util.List;

public interface UserManager {
    public User getUserByName(String name);
    public User getUserByID(String id);
    public String addUser(User user);
    public User updateUser(String newName,String newPassword, String newEmail , String oldID);
    //public void deleteUser(String id); TO DO
    public String checkLogin(User user);
}
