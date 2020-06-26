package edu.upc.dsa.util;

import edu.upc.dsa.DAO.*;
import edu.upc.dsa.models.*;

import java.util.ArrayList;
import java.util.List;

public class GameManagerImp implements GameManager{


    private static GameManagerImp instance;
    private LevelDAOImp levelDB;
    private GameDAO gameDB;
    private UserDAO userDB;
    private BestLevelDAO historyDB;

    private GameManagerImp() {
        userDB = new UserDAOImp();
        gameDB = new GameDAOImp();
        levelDB = new LevelDAOImp();
        historyDB = new BestLevelDAOImp();
    }

    public static GameManagerImp getInstance() {
        if (instance==null) instance = new GameManagerImp();
        return instance;
    }

    @Override
    public void addLevel(LevelTO lvl) {

        int lvlCount = levelDB.countLevels();
        Level finalLvl = new Level(lvl.getMap(), lvlCount + 1);

        levelDB.addLevel(finalLvl);
    }

    @Override
    public LevelTO getLevel(int lvlNumber) {
        LevelTO lvl = new LevelTO();
        Level dbLevel = levelDB.getLevel(lvlNumber);
        lvl.setMap(dbLevel.getMap());
        lvl.setLvlNumber(dbLevel.getLevelNumber());
        return lvl;
    }

    @Override
    public Game getGame(String idUser) {
        User user = userDB.getUser("id", idUser);
        return gameDB.getGame(user);
    }

    @Override
    public Game levelCompleted(CompletedLevel lvl) {
        User user = userDB.getUser("id", lvl.getIdUser());
        Game game = gameDB.getGame(user);
        BestLevel bestLevel = historyDB.getBestLevel(lvl.getLevelNumber(), user);

        if (lvl.getKeepsMask()) {
            game.setMask("TRUE");
        }
        else {
            game.setMask("FALSE");
        }

        int expToNextLevel = (int) ((Math.pow(user.getLevel(),2))*100);

        int currentExp = user.getExp() + lvl.getScore();

        boolean levelUp = currentExp > expToNextLevel;

        while (levelUp) {
            user.setLevel(user.getLevel()+1);
            currentExp -= expToNextLevel;
            expToNextLevel = (int) ((Math.pow(user.getLevel(),2))*100);
            levelUp = currentExp >= expToNextLevel;
        }
        user.setExp(currentExp);

        userDB.updateUser(user);

        if (game.getCompletedLevels() < lvl.getLevelNumber())
            game.setCompletedLevels(lvl.getLevelNumber());
        game.setCash(game.getCash() + lvl.getScore());
        gameDB.updateGame(game, user);

        if (bestLevel == null) {
            BestLevel newBestLevel = new BestLevel(lvl.getLevelNumber(), lvl.getScore(), lvl.getTime(), lvl.getIdUser());
            historyDB.saveBestLevel(newBestLevel);
        }

        else if  (bestLevel.getBestScore() < lvl.getScore()) {
            bestLevel.setBestScore(lvl.getScore());
            bestLevel.setBestTime(lvl.getTime());
            bestLevel.setStartDate(lvl.getStartTime());
            historyDB.updateBestLevel(bestLevel);
        }
        return game;
    }

    @Override
    public ArrayList<LevelTO> getAllLevels() {
        List<Level> levels = levelDB.listAllLevels();
        ArrayList<LevelTO> lvlsTO = new ArrayList<LevelTO>();

        for (Level lvl : levels) {
            LevelTO lvlTO = new LevelTO();
            lvlTO.setMap(lvl.getMap());
            lvlTO.setLvlNumber(lvl.getLevelNumber());
            lvlsTO.add(lvlTO);
        }
        return lvlsTO;
    }
}
