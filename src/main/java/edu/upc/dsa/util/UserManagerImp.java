package edu.upc.dsa.util;

import dsa.grupo2.UserDaoImp;
import dsa.grupo2.models.User;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class UserManagerImp implements UserManager {

    final static Logger logger = Logger.getLogger(UserManagerImp.class);

    private static UserManagerImp instance;

    private UserDaoImp userDB;


    private UserManagerImp() {
        userDB = new UserDaoImp();
    }

    public static UserManagerImp getInstance() {
        if (instance==null) instance = new UserManagerImp();
        return instance;
    }


    @Override
    public User getUserByName(String name) {
        return userDB.getUserByName(name);
    }

    @Override
    public User getUserByID(String id) {
        return userDB.getUser("ID", id);
    }

    @Override
    public String addUser(User user) {
        User test = userDB.getUserByName(user.getName());
        String id;

        if (test == null) {
            id = userDB.addUser(user.getName(),user.getEmail(), user.getPassword());
        }
        else {
            id = null;
        }
        return id;
    }

    @Override
    public User updateUser(String newName, String newPassword, String newEmail, String oldID) {
        userDB.updateUser(newName, newEmail, newPassword, oldID);
        return userDB.getUser("ID", oldID);
    }

    @Override
    public String checkLogin(User user) {
        return userDB.checkLogin(user.getName(), user.getPassword());
    }
}
