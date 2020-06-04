package edu.upc.dsa.DAO;

import dsa.grupo2.FactorySession;
import dsa.grupo2.Session;
import edu.upc.dsa.models.BestLevel;
import edu.upc.dsa.models.User;

import java.util.ArrayList;
import java.util.HashMap;

public class BestLevelDAOImp implements BestLevelDAO{

    @Override
    public void saveBestLevel(BestLevel bestLevel) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.save(bestLevel);
        }
        catch (Exception e) {
            int c = 1;
        }
        finally {
            session.close();
        }
    }

    @Override
    public ArrayList<BestLevel> getBestLevelsByUser(User user) {
        Session session = null;
        ArrayList<BestLevel> bestLevels = null;
        try {
            session = FactorySession.openSession();
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("idUser", user.getId());
            bestLevels = (ArrayList<BestLevel>) session.findAll(BestLevel.class,params);
        }
        catch (Exception e) {
        }
        finally {
            session.close();
        }
        return bestLevels;
    }

    @Override
    public ArrayList<BestLevel> getBestLevelsByLevel(int lvlNumber) {
        Session session = null;
        ArrayList<BestLevel> bestLevels = null;
        try {
            session = FactorySession.openSession();
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("levelNumber", Integer.toString(lvlNumber));
            bestLevels = (ArrayList<BestLevel>) session.findAll(BestLevel.class,params);
        }
        catch (Exception e) {
        }
        finally {
            session.close();
        }
        return bestLevels;
    }

    @Override
    public BestLevel updateBestLevel(BestLevel level) {
        return null;
    }
}
