package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Level;

import java.util.ArrayList;

public interface LevelDAO {
    Level getLevel(int levelNumber);
    Level addLevel(Level level);
    ArrayList<Level> listAllLevels();
}
