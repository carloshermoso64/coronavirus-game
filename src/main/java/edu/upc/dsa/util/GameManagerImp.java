package edu.upc.dsa.util;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GameManagerImp implements edu.upc.dsa.util.GameManager {

    private HashMap<String, Usuario> allUsers;
    final static Logger logger = Logger.getLogger(GameManagerImp.class);

    private static edu.upc.dsa.util.GameManager instance;


    private GameManagerImp(){
        this.allUsers = new HashMap<String, Usuario>();
    }

    public static edu.upc.dsa.util.GameManager getInstance() {
        if (instance==null) instance = new GameManagerImp();
        return instance;
    }
    @Override
    public HashMap<String,Usuario> getUsers(){
        return this.allUsers;
    }

    @Override
    public List<Usuario> getUsersList() {
        List<Usuario> userList = new LinkedList<Usuario>(this.allUsers.values());
        Collections.sort(userList);
        return userList;
    }

    @Override
    public void addUser(String name, String lastname,String id) {
        Usuario u = new Usuario(name,lastname, id);
        this.allUsers.put(u.getId(),u);
        logger.info(u.getNombre() + " " + " registered with id: " + u.getId());
    }
    @Override
    public void addUser(Usuario usuario){
        this.allUsers.put(usuario.getId(),usuario);
        logger.info(usuario.getNombre() + " " + " registered with id: " + usuario.getId());
    }


    @Override
    public void updateUser(String name, String mail, String id) {
        Usuario u = this.allUsers.get(id);
        this.allUsers.remove(id);
        u.setCorreo(mail);
        u.setNombre(name);
        this.allUsers.put(id,u);
        logger.info("User modified to: " + u.getNombre() + " with id: " + u.getId() + " and mail: " + u.getCorreo());
    }

    @Override
    public int getNumUsers() {
        return allUsers.size();
    }

    @Override
    public Usuario getUser(String iduser) {
        return getUsers().get(iduser);
    }

    @Override
    public void clear() {
        instance = null;
        this.allUsers.clear();
        logger.info("Closing..........");
    }
}
