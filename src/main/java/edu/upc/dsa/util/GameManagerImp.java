package edu.upc.dsa.util;

import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GameManagerImp implements edu.upc.dsa.util.GameManager {

    private HashMap<String, User> allUsers;
    final static Logger logger = Logger.getLogger(GameManagerImp.class);

    private static edu.upc.dsa.util.GameManager instance;


    private GameManagerImp(){
        this.allUsers = new HashMap<String, User>();
    }

    public static edu.upc.dsa.util.GameManager getInstance() {
        if (instance==null) instance = new GameManagerImp();
        return instance;
    }
    @Override
    public HashMap<String, User> getUsers(){
        return this.allUsers;
    }

    @Override
    public List<User> getUsersList() {
        List<User> userList = new LinkedList<User>(this.allUsers.values());
        Collections.sort(userList);
        return userList;
    }

    @Override
    public void addUser(String name, String lastname,String id) {
        User u = new User(name,lastname, id);
        this.allUsers.put(u.getId(),u);
        logger.info(u.getName() + " " + " registered with id: " + u.getId());
    }
    @Override
    public void addUser(User user){
        this.allUsers.put(user.getId(), user);
        logger.info(user.getName() + " " + " registered with id: " + user.getId());
    }


    @Override
    public void updateUser(String name, String mail, String id) {
        User u = this.allUsers.get(id);
        this.allUsers.remove(id);
        u.setEmail(mail);
        u.setName(name);
        this.allUsers.put(id,u);
        logger.info("User modified to: " + u.getName() + " with id: " + u.getId() + " and mail: " + u.getEmail());
    }

    @Override
    public int getNumUsers() {
        return allUsers.size();
    }

    @Override
    public User getUser(String iduser) {
        return getUsers().get(iduser);
    }

    @Override
    public void clear() {
        instance = null;
        this.allUsers.clear();
        logger.info("Closing..........");
    }
}
