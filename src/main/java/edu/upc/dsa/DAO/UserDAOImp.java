package edu.upc.dsa.DAO;

import dsa.grupo2.FactorySession;
import dsa.grupo2.Session;
import edu.upc.dsa.models.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDAOImp  implements UserDAO{

    @Override
    public String addUser(String name, String email, String password) {
        Session session = null;
        String userID = null;
        try {
            User user = new User(name, email, password);
            session = FactorySession.openSession();
            session.save(user);
            userID = user.getId();
        }

        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return userID;
    }

    @Override
    public User getUser(String searchField, String value) {
        Session session = null;
        User user = new User();
        try {
            session = FactorySession.openSession();
            user= (User)session.get(User.class,searchField, value);
        }
        catch (Exception e) {

        }
        finally {
            session.close();
        }
        return user;
    }

    @Override
    public User updateUser(String newName, String newEmail, String newPassword, String id) {
        Session session = null;
        User user = new User();
        try {
            session = FactorySession.openSession();
            user = (User)session.get(User.class, "ID" , id);
            User newUser = new User(newName, newEmail, newPassword );
            newUser.setId(id);
            newUser.setExp(user.getExp());
            session.update(newUser,id);
        }
        catch (Exception e) {
            return null;
        }
        finally {
            session.close();
        }
        return this.getUser("ID", id);
    }

    @Override
    public User getUserByName(String name) {
        Session session = null;
        User user = new User();
        try {
            session = FactorySession.openSession();
            user= (User)session.get(User.class,"name", name);
        }
        catch (Exception e) {

        }
        finally {
            session.close();
        }
        return user;
    }

    @Override
    public String checkLogin(String name, String password) {
        Session session = null;
        HashMap<String, String > params = new HashMap<String, String>();
        params.put("name", name);
        params.put("password",password);
        try {
            session = FactorySession.openSession();
            Integer i = session.count(User.class, params);
            if (i == 1) {
                User u = this.getUserByName(name);
                TokenDAOImp td = new TokenDAOImp();
                String tokId = td.addToken(u.getId(),"false");
                return tokId;
            }
            else
                return "FALSE";
        }
        catch (Exception e) {
            //Log
            return null;
        }
        finally {
            session.close();
        }
    }

    public boolean deleteUser(User user) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(user);
            return true;
        }
        catch (Exception e) {
            return false;
        }
        finally {
            session.close();
        }
    }

    public ArrayList<User> getAllUsers() {
        Session session = null;
        ArrayList<User> users = null;
        try {
            session = FactorySession.openSession();
            users = (ArrayList<User>) session.findAll(User.class);
        }
        catch (Exception e) {

        }
        finally {
            session.close();
        }
        return users;
    }
}
