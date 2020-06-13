package edu.upc.dsa.DAO;

import dsa.grupo2.FactorySession;
import dsa.grupo2.Session;
import edu.upc.dsa.models.Token;
import edu.upc.dsa.models.User;

import java.util.HashMap;

public class TokenDAOImp implements TokenDAO {

    @Override
    public String addToken(String userId, String adminRights) {
        Session session = null;
        String userID = null;
        Token t;
        try {
            t = new Token(userId,adminRights);
            session = FactorySession.openSession();
            session.save(t);
            return t.getId();
        }

        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return null;
    }

    @Override
    public void delToken(String id) {

    }

    @Override
    public boolean checkToken(String id) {
        Session session = null;
        String userID = null;
        try {
            session = FactorySession.openSession();
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("id",id);
            Integer count = session.count(Token.class,params);
            if (count == 1)
                return true;
            else
                return false;
        }

        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return false;
    }
    @Override
    public boolean isAdmin(String id) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            Token t = (Token) session.get(Token.class, "id", id);
            if (t.getAdmin().equals("true")) {
                return true;
            }
            else {
                return false;
            }
        }

        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return false;
    }

    public Token getToken(User user) {
        Session session = null;
        try {
            Token t = null;
            session = FactorySession.openSession();
            t = (Token) session.get(Token.class, "idUser", user.getId());
            return t;
        }

        catch (Exception e) {
            return null;
        }
        finally {
            session.close();
        }
    }

    public void delToken(Token t) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(t);
        }

        catch (Exception e) {
        }
        finally {
            session.close();
        }
    }
}