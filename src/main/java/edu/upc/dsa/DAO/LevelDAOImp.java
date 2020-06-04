package edu.upc.dsa.DAO;

import dsa.grupo2.FactorySession;
import dsa.grupo2.Session;
import edu.upc.dsa.models.Level;


import java.util.ArrayList;

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

}
