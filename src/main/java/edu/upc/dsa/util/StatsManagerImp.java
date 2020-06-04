package edu.upc.dsa.util;

import edu.upc.dsa.DAO.*;
import edu.upc.dsa.models.BestLevel;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StatsManagerImp implements StatsManager{

    private static StatsManagerImp instance;
    UserDAO userDB;
    BestLevelDAO levelDB;


    private StatsManagerImp() {
        userDB = new UserDAOImp();
        levelDB = new BestLevelDAOImp();
    }

    public static StatsManagerImp getInstance() {
        if (instance==null)
            instance = new StatsManagerImp();
        return instance;
    }

    @Override
    public ArrayList<BestLevel> getSortedBestLevelsOfUser(String name) {
        User user = userDB.getUser("name", name);
        ArrayList<BestLevel> levels = levelDB.getBestLevelsByUser(user);

        Collections.sort(levels, new Comparator<BestLevel>() {
            @Override
            public int compare(BestLevel o1, BestLevel o2) {
                return o1.getLevelNumber() - o2.getLevelNumber();
            }
        });
        return  levels;
    }

    @Override
    public ArrayList<BestLevel> getSortedBestScoresOfLevel(int levelNumber) {
        ArrayList<BestLevel> levels = levelDB.getBestLevelsByLevel(levelNumber);
        Collections.sort(levels, new Comparator<BestLevel>() {
            @Override
            public int compare(BestLevel o1, BestLevel o2) {
                return o2.getBestScore() - o1.getBestScore();
            }
        });
        return levels;
    }

    @Override
    public ArrayList<UserTO> getUsersByLevel() {
        ArrayList<User> users = userDB.getAllUsers();
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.getLevel() - o1.getLevel();
            }
        });
        ArrayList<UserTO> userTOArrayList = new ArrayList<UserTO>();
        for (User u : users) {
            UserTO userTO = new UserTO();
            userTO.setTO(u);
            userTOArrayList.add(userTO);
        }
        return userTOArrayList;
    }
}
