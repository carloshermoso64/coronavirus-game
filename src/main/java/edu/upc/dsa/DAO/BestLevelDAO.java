package edu.upc.dsa.DAO;

import edu.upc.dsa.models.BestLevel;
import edu.upc.dsa.models.User;

import java.util.ArrayList;

public interface BestLevelDAO {

    void saveBestLevel(BestLevel bestLevel);
    ArrayList<BestLevel> getBestLevelsByUser(User user);
    ArrayList<BestLevel> getBestLevelsByLevel(int lvlNumber);
    BestLevel updateBestLevel(BestLevel level);
}
