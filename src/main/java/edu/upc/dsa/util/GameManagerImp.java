package edu.upc.dsa.util;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.text.CollationElementIterator;
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
    public List<User> getUsers() {
        List<User> userList = new LinkedList<User>(this.allUsers.values());
        Collections.sort(userList);
        return userList;
    }

    @Override
    public void addUser(String name, String lastname,String id) {
        User u = new User(name,lastname, id);
        this.allUsers.put(u.getId(),u);
        logger.info(u.getName() + " " + u.getLastname() + " registered with id: " + u.getId());
    }

    @Override
    public void updateUser(String name, String lastname, String mail, String id) {
        User u = this.allUsers.get(id);
        this.allUsers.remove(id);
        u.setMail(mail);
        u.setName(name);
        u.setLastname(lastname);
        this.allUsers.put(id,u);
        logger.info("User modified to: " + u.getName() + " " + u.getLastname() + " with id: " + u.getId() + " and mail: " + u.getMail());
    }

    @Override
    public int getNumUsers() {
        return allUsers.size();
    }

    @Override
    public User getUser(String iduser) {
        return allUsers.get(iduser);
    }

    @Override
    public void addObjectUser(String iduser, String idobj,String descp) {
        User u = this.allUsers.get(iduser);
        Objeto obj = new Objeto(idobj,descp);
        u.addObject(obj);
        this.allUsers.put(iduser,u);
        logger.info(u.getId() + " received: " + obj.getId());
    }

    @Override
    public int getnumObjectsbyUser(String iduser) {
        User u = this.allUsers.get(iduser);
        return u.getObjects().size();
    }

    @Override
    public void clear() {
        instance = null;
        this.allUsers.clear();
        logger.info("Closing..........");
    }
}
