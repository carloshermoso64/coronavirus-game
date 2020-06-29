package edu.upc.dsa.util;

import edu.upc.dsa.models.CompletedLevel;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Level;
import edu.upc.dsa.models.LevelTO;

import java.util.List;

public interface GameManager {

    void addLevel(LevelTO lvl);
    LevelTO getLevel(int lvlNumber);
    List<LevelTO> getAllLevels();
    Game getGame(String idUser);
    Game levelCompleted(CompletedLevel lvl);
    void addLevel(String levelString);
}
