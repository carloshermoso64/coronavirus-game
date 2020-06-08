package edu.upc.dsa.DAO;

import dsa.grupo2.FactorySession;
import dsa.grupo2.Session;
import edu.upc.dsa.models.Level;


import java.util.ArrayList;
import java.util.HashMap;

public class LevelDAOImp implements LevelDAO {

    @Override
    public Level getLevel(int levelNumber) {
        Session session = null;
        Level level = null;
        try {
            session = FactorySession.openSession();
            level = (Level) session.get(Level.class,"levelNumber", Integer.toString(levelNumber));
        }
        catch (Exception e) {

        }
        finally {
            session.close();
        }
        return level;
    }

    @Override
    public Level addLevel(Level level) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.save(level);

        }
        catch (Exception e) {

        }
        finally {
            session.close();
        }
        return level;
    }

    @Override
    public ArrayList<Level> listAllLevels() {
        Session session = null;
        ArrayList<Level> levels = null;
        try {
            session = FactorySession.openSession();
            levels = (ArrayList<Level>) session.findAll(Level.class);
        }
        catch (Exception e) {

        }
        finally {
            session.close();
        }
        return levels;
    }

    @Override
    public int countLevels() {
        Session session = null;
        int levelsNum = 0;
        try {
            session = FactorySession.openSession();
            HashMap<String,String> params = new HashMap<String, String>();
            levelsNum= session.count(Level.class, params);
        }

        catch (Exception e) {
            return -1;
        }
        finally {
            session.close();
        }
        return levelsNum;
    }

}
