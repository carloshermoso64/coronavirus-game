package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.User;

public interface GameDAO {
    public Game createGame(User user);
    public Game getGame(User user);
    public Game updateGame(Game game, User user);
}
