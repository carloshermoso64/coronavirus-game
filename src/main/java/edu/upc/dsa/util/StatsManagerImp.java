package edu.upc.dsa.util;

import edu.upc.dsa.DAO.*;
import edu.upc.dsa.models.BestLevel;
import edu.upc.dsa.models.BestLevelTO;
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
    public ArrayList<BestLevelTO> getSortedBestLevelsOfUser(String name) {
        User user = userDB.getUser("name", name);
        ArrayList<BestLevel> levels = levelDB.getBestLevelsByUser(user);
        ArrayList<BestLevelTO> levelTOS = new ArrayList<BestLevelTO>();

        Collections.sort(levels, new Comparator<BestLevel>() {
            @Override
            public int compare(BestLevel o1, BestLevel o2) {
                return o1.getLevelNumber() - o2.getLevelNumber();
            }
        });

        for (BestLevel lvl : levels) {
            levelTOS.add(this.convertToTrans(lvl));
        }

        return  levelTOS;
    }

    @Override
    public ArrayList<BestLevelTO> getSortedBestScoresOfLevel(int levelNumber) {
        ArrayList<BestLevel> levels = levelDB.getBestLevelsByLevel(levelNumber);
        ArrayList<BestLevelTO> levelTOS = new ArrayList<BestLevelTO>();
        Collections.sort(levels, new Comparator<BestLevel>() {
            @Override
            public int compare(BestLevel o1, BestLevel o2) {
                return o2.getBestScore() - o1.getBestScore();
            }
        });

        for (BestLevel lvl : levels) {
            levelTOS.add(this.convertToTrans(lvl));
        }

        return  levelTOS;
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


    public BestLevelTO convertToTrans(BestLevel lvl) {
        BestLevelTO bestLevelTO = new BestLevelTO();

        bestLevelTO.setId(lvl.getId());
        bestLevelTO.setBestScore(lvl.getBestScore());
        bestLevelTO.setBestTime(lvl.getBestTime());
        bestLevelTO.setLevelNumber(lvl.getLevelNumber());
        bestLevelTO.setStartDate(lvl.getStartDate());

        User user = userDB.getUser("id", lvl.getIdUser());
        bestLevelTO.setUsername(user.getName());

        return bestLevelTO;
    }
}
