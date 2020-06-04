package edu.upc.dsa.util;

import edu.upc.dsa.models.BestLevel;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserTO;

import java.util.ArrayList;

public interface StatsManager {

    ArrayList<BestLevel>getSortedBestLevelsOfUser(String name);
    ArrayList<BestLevel>getSortedBestScoresOfLevel(int levelNumber);
    ArrayList<UserTO>getUsersByLevel();

}
