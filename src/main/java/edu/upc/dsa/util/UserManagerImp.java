package edu.upc.dsa.util;

import edu.upc.dsa.DAO.GameDAOImp;
import edu.upc.dsa.DAO.TokenDAOImp;
import edu.upc.dsa.DAO.UserDAOImp;
import edu.upc.dsa.models.Token;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class UserManagerImp implements UserManager {

    final static Logger logger = Logger.getLogger(UserManagerImp.class);

    private static UserManagerImp instance;

    private UserDAOImp userDB;
    private GameDAOImp gameDB;
    private TokenDAOImp tokenDB;


    private UserManagerImp() {
        userDB = new UserDAOImp();
        gameDB = new GameDAOImp();
        tokenDB = new TokenDAOImp();
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
        return userDB.getUser("id", id);
    }

    @Override
    public String addUser(User user) {
        User test = userDB.getUserByName(user.getName());
        String id;

        if (test == null) {
            id = userDB.addUser(user.getName(),user.getEmail(), user.getPassword());
            User newUser = userDB.getUser("id", id);
            gameDB.createGame(newUser);
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

        user = userDB.getUser("name", user.getName());
        Token t = (tokenDB.getToken(user));

        if (t != null) {
            return t.getId();
        }
        else {
            return userDB.checkLogin(user.getName(), user.getPassword());
        }
    }

    public boolean deleteUser(String id) {
        User u = getUserByID(id);
        boolean deleted = userDB.deleteUser(u);
        return deleted;
    }

    public boolean logOut(Token t) {
        boolean exists = tokenDB.checkToken(t.getId());
        if(exists) {
            User u = userDB.getUser("id", t.getIdUser());
            Token trueToken = tokenDB.getToken(u);
            if (trueToken.getId().equals(t.getId())) {
                tokenDB.delToken(t);
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
}
